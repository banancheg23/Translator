package com.banancheg.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.banancheg.core.base.BaseActivity
import com.banancheg.historyscreen.history.HistoryActivity
import com.banancheg.model.data.AppState
import com.banancheg.model.data.DataModel
import com.banancheg.translator.R
import com.banancheg.translator.databinding.ActivityMainBinding
import com.banancheg.translator.view.descriptionscreen.DescriptionActivity
import com.banancheg.utils.convertMeaningsToString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope
import java.lang.IllegalStateException

class MainActivity : BaseActivity<AppState>(), AndroidScopeComponent {

    companion object {
        private val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "42"
    }

    override val scope: Scope by activityScope()
    override val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy { MainAdapter(::onItemClick) }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.menu_find_saved_word -> {
                showInputDialog("Input word")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onAccessDialogInput(text: String) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getWordInfo(text, false)
                .flowOn(Dispatchers.IO)
                .collect() {
                    when (it) {
                        is AppState.Success -> {
                            val appStateData = it.data
                            if (!appStateData.isNullOrEmpty())
                                startActivity(
                                    DescriptionActivity.getIntent(
                                        this@MainActivity,
                                        appStateData[0].text ?: "",
                                        appStateData[0].meanings?.get(0)?.translation?.translation ?: "",
                                        appStateData[0].meanings?.get(0)?.imageUrl
                                    )
                                )
                        }

                        else -> showAlertDialog(getString(R.string.error_stub), getString(R.string.find_word_error))
                    }
                }
        }
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("ViewModel must be initialised first")
        }

        viewModel.subscribe().observe(this) {
            renderData(it)
        }
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun onItemClick(data: DataModel) {
        startActivity(
            DescriptionActivity.getIntent(
                this@MainActivity,
                data.text ?: "",
                convertMeaningsToString(data.meanings),
                data.meanings?.get(0)?.imageUrl
            )
        )
    }
}