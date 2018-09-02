package com.khairilushan.androidsetup.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.khairilushan.domain.interactor.Search
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
class SearchViewModelFactory @Inject constructor(
    private val search: Search
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModelImpl::class.java)) {
            return SearchViewModelImpl(search) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
