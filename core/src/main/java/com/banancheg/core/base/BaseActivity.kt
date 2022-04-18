package com.banancheg.core.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.banancheg.core.R
import com.banancheg.core.databinding.LoadingLayoutBinding
import com.banancheg.core.viewmodel.BaseViewModel
import com.banancheg.model.data.AppState
import com.banancheg.utils.network.OnlineLiveData
import com.banancheg.utils.ui.AlertDialogFragment
import com.banancheg.utils.ui.InputDialogFragment

abstract class BaseActivity<T : AppState> : AppCompatActivity(), View {

    private lateinit var binding: LoadingLayoutBinding
    abstract val viewModel: BaseViewModel<T>
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoadingLayoutBinding.inflate(layoutInflater)
        subscribeToNetworkChange()
    }

    private fun subscribeToNetworkChange() {
        OnlineLiveData(this).observe(this) { isOnline ->
            isNetworkAvailable = isOnline
            if (!isNetworkAvailable) {
                Toast.makeText(
                    this,
                    R.string.dialog_message_device_is_offline,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    private fun showNoInternetConnectionDialog() {
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

    open fun onAccessDialogInput(text: String) {}

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
                val progress = appState.progress
                if (progress != null) {
                    binding.progressBarHorizontal.visibility = android.view.View.VISIBLE
                    binding.progressBarRound.visibility = android.view.View.GONE
                    binding.progressBarHorizontal.progress = progress
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