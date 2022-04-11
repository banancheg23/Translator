package com.banancheg.translator.view.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.banancheg.translator.R
import com.banancheg.translator.databinding.LoadingLayoutBinding
import com.banancheg.translator.model.data.AppState
import com.banancheg.translator.utils.convertMeaningsToString
import com.banancheg.translator.utils.network.isOnline
import com.banancheg.translator.utils.ui.AlertDialogFragment
import com.banancheg.translator.utils.ui.InputDialogFragment
import com.banancheg.translator.view.descriptionscreen.DescriptionActivity
import com.banancheg.translator.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    private lateinit var binding: LoadingLayoutBinding
    abstract val viewModel: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        binding = LoadingLayoutBinding.inflate(layoutInflater)

        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message).show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    protected fun showInputDialog(title: String?) {
        InputDialogFragment.newInstance(this, title, ::onAccessDialogInput).show()
    }

    open fun onAccessDialogInput(text: String) {
        startActivity(
            DescriptionActivity.getIntent(
                this,
                text,
                text,
                null
            )
        )
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val dataModel = appState.data
                if (dataModel.isNullOrEmpty()) {
                    showAlertDialog(getString(R.string.error_stub), getString(R.string.empty_server_response_on_success))
                    return
                }

                setDataToAdapter(dataModel)
            }

            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = android.view.View.VISIBLE
                    binding.progressBarRound.visibility = android.view.View.GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = android.view.View.GONE
                    binding.progressBarRound.visibility = android.view.View.VISIBLE
                }
            }

            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = android.view.View.GONE

    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = android.view.View.VISIBLE
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}