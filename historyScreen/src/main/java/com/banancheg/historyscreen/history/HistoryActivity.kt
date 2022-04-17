package com.banancheg.historyscreen.history

import android.os.Bundle
import android.view.MenuItem
import com.banancheg.core.base.BaseActivity
import com.banancheg.historyscreen.databinding.ActivityHistoryBinding
import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.IllegalStateException

class HistoryActivity : BaseActivity<AppState>() {

    private lateinit var binding: ActivityHistoryBinding
    override val viewModel by lazy { getViewModel<HistoryViewModel>() }
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setActionbarHomeAsUp()
        initViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData("", false)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setActionbarHomeAsUp() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }

    private fun initViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("ViewModel must be initialised first")
        }

        viewModel.subscribe().observe(this) {
            renderData(it)
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
}