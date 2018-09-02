package com.khairilushan.androidsetup.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.khairilushan.domain.interactor.Result
import com.khairilushan.domain.interactor.Search
import com.khairilushan.domain.model.Repo
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */

const val DEFAULT_KEYWORD = "android"

interface SearchViewModel {
    val inputs: Inputs
    val outputs: Outputs

    interface Inputs {
        fun onKeywordChanged(keyword: String)
        fun onViewDidAppear()
    }

    interface Outputs {
        val keyword: String
        fun getItemViewModels(): LiveData<List<RepoItemViewModel>>
        fun showErrorMessage(): LiveData<String?>
    }
}

class SearchViewModelImpl @Inject constructor(
    private val search: Search
) : ViewModel(), SearchViewModel, SearchViewModel.Inputs, SearchViewModel.Outputs {

    private val itemViewModels = MutableLiveData<List<RepoItemViewModel>>()
    private val errorMessage = MutableLiveData<String?>()
    private var job = Job()
    private var currentKeyword = DEFAULT_KEYWORD

    override val inputs: SearchViewModel.Inputs = this
    override val outputs: SearchViewModel.Outputs = this
    override val keyword: String = currentKeyword

    private fun search() {
        val params = Search.Params(currentKeyword)
        search.execute(params) {
            when (it) {
                is Result.Success -> mapToItemViewModels(it.result)
                is Result.Failure -> errorMessage.value = it.message
            }
        }
    }

    private fun mapToItemViewModels(list: List<Repo>) {
        itemViewModels.value = list.map { RepoItemViewModel.fromRepo(it) }
    }

    override fun getItemViewModels(): LiveData<List<RepoItemViewModel>> = itemViewModels

    override fun showErrorMessage(): LiveData<String?> = errorMessage

    override fun onKeywordChanged(keyword: String) {
        if (keyword == currentKeyword) return
        currentKeyword = if (keyword.isEmpty()) DEFAULT_KEYWORD else keyword
        job.cancel()
        job = launch {
            delay(400)
            if (!isActive) return@launch
            search()
        }
    }

    override fun onViewDidAppear() {
        errorMessage.value = null
        search()
    }

    override fun onCleared() {
        search.dispose()
        super.onCleared()
    }
}
