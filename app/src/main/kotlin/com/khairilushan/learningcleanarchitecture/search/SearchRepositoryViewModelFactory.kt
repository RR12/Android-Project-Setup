package com.khairilushan.learningcleanarchitecture.search

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.khairilushan.domain.interactor.SearchRepository
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
class SearchRepositoryViewModelFactory
@Inject constructor(private val searchRepository: SearchRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchRepositoryViewModel::class.java)) {
            return SearchRepositoryViewModel(searchRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}