package com.khairilushan.learningcleanarchitecture.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.jakewharton.rxbinding2.widget.RxTextView
import com.khairilushan.learningcleanarchitecture.R
import com.khairilushan.learningcleanarchitecture.shared.extension.disposed
import com.khairilushan.learningcleanarchitecture.shared.extension.showShortToast
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_search_repo.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchRepositoryActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory: SearchRepositoryViewModelFactory

    private val mViewModel: SearchRepositoryViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
                .get(SearchRepositoryViewModel::class.java)
    }
    private val mAdapter = SearchRepositoryAdapter()
    private val mDisposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_repo)

        setupRecyclerView()
        bindViewModel()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }

    private fun bindViewModel() {
        searchEditText.setText(mViewModel.currentKeyword)

        mViewModel.getItemViewModels().observe(this, Observer {
            it?.let {
                mAdapter.updateItems(it)
            }
        })

        mViewModel.showErrorMessage().observe(this, Observer {
            it?.let { message ->
                showShortToast(this, message)
            }
        })

        RxTextView.afterTextChangeEvents(searchEditText)
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mViewModel.search(searchEditText.text.toString())
                }
                .disposed(mDisposables)
    }

}
