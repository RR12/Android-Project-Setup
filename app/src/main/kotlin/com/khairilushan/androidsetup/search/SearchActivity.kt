package com.khairilushan.androidsetup.search

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.khairilushan.androidsetup.R
import com.khairilushan.androidsetup.shared.extension.doAfterTextChanged
import com.khairilushan.androidsetup.shared.extension.observeViewModel
import com.khairilushan.androidsetup.shared.extension.showShortToast
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search_repo.*
import javax.inject.Inject

class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    private val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(SearchViewModelImpl::class.java)
    }

    private val mAdapter = SearchRepositoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_repo)

        setupRecyclerView()
        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.inputs.onViewDidAppear()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mAdapter
    }

    private fun bindViewModel() {
        searchEditText.setText(viewModel.outputs.keyword)

        observeViewModel(viewModel.outputs.getItemViewModels()) { items ->
            items?.let { mAdapter.updateItems(it) }
        }

        observeViewModel(viewModel.outputs.showErrorMessage()) {
            it?.let { message -> showShortToast(this, message) }
        }

        searchEditText.doAfterTextChanged { viewModel.inputs.onKeywordChanged(it) }
    }

}
