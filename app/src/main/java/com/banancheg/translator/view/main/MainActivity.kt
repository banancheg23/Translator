package com.banancheg.translator.view.main

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.banancheg.translator.R
import com.banancheg.translator.databinding.ActivityMainBinding
import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.model.data.DataModel
import com.banancheg.translator.view.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.IllegalStateException

class MainActivity : BaseActivity<AppState>() {

    companion object {
        private val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "42"
    }

    override lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener = object : MainAdapter.OnListItemClickListener {
        override fun onItemClick(data: DataModel) {
            Toast.makeText(this@MainActivity, data.text, Toast.LENGTH_SHORT).show()
        }
    }

    private val fabClickListener = View.OnClickListener {
        val searchDialogFragment = SearchDialogFragment.newInstance()
        searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
        searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
    }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener = object : SearchDialogFragment.OnSearchClickListener {
        override fun onClick(searchWord: String) {
            viewModel.getData(searchWord, true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initViews()
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("ViewModel must be initialised first")
        }

        viewModel = getViewModel<MainViewModel>()
        viewModel.subscribe().observe(this) {
            renderData(it)
        }
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val dataModel = appState.data
                if (dataModel == null || dataModel.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response_on_success))
                    return
                }

                showViewSuccess()
                adapter?.setData(dataModel)
            }

            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }

            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        showViewError()
        binding.errorTextview.text = error ?: getString(R.string.undefined_error)
        binding.reloadButton.setOnClickListener {
            viewModel.getData("hi", true)
        }
    }

    private fun showViewSuccess() {
        binding.successLinearLayout.visibility = VISIBLE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = VISIBLE
        binding.errorLinearLayout.visibility = GONE
    }

    private fun showViewError() {
        binding.successLinearLayout.visibility = GONE
        binding.loadingFrameLayout.visibility = GONE
        binding.errorLinearLayout.visibility = VISIBLE
    }
}