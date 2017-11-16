package com.khairilushan.learningcleanarchitecture.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.khairilushan.domain.interactor.SearchRepository
import com.khairilushan.domain.model.Repo
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
class SearchRepositoryViewModel
@Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

    private val mItemViewModels = MutableLiveData<List<RepoItemViewModel>>()
    private val mErrorMessage = MutableLiveData<String?>()
    var currentKeyword = ""

    fun search(keyword: String) {
        if (keyword != currentKeyword) {
            currentKeyword = keyword
        }
        val params = SearchRepository.Params(keyword)
        searchRepository
                .execute({ repos ->
                    mapToItemViewModels(repos)
                }, { error ->
                    mErrorMessage.value = error.localizedMessage
                    mErrorMessage.value = null
                }, params)
    }

    private fun mapToItemViewModels(list: List<Repo>) {
        mItemViewModels.value = list.map { RepoItemViewModel.fromRepo(it) }
    }

    fun getItemViewModels(): LiveData<List<RepoItemViewModel>> = mItemViewModels

    fun showErrorMessage(): LiveData<String?> = mErrorMessage

    override fun onCleared() {
        searchRepository.dispose()
        super.onCleared()
    }
}