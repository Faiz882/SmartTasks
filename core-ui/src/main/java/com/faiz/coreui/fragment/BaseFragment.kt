package com.faiz.coreui.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.faiz.coreui.errors.ApiErrorHandler.Companion.ERROR_CODE_OFFLINE
import com.faiz.coreui.errors.ApiErrorHandler.Companion.ERROR_CODE_TIMEOUT
import com.faiz.coreui.ui.ProgressDialog
import com.faiz.coreui.viewmodel.BaseViewModel
import com.faiz.coreui.viewmodel.ProgressType
import com.faiz.coreui.viewmodel.ViewModelState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(val bindingFactory: (LayoutInflater) -> VB) :
    Fragment() {


    companion object {
        // To prevent user from quickly pressing back button which causes empty screen on previous fragment.
        const val BACK_PRESS_DELAY = 600L
    }

    private var _viewBinding: VB? = null

    fun viewBinding(): VB {
        if (_viewBinding == null) throw NullPointerException("viewBinding is null!!")
        return _viewBinding!!
    }

    protected val viewModel: VM by lazy {
        ViewModelProvider(this).get((this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>)
    }

    private var progressDialog: ProgressDialog? = null
        get() {
            if (field == null) field = ProgressDialog()
            return field
        }

    private var currentProgress: ProgressType? = null

    private var isBackPressedEnabled = false

    private val backPressCallback: OnBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!isBackPressedEnabled) return
                onBackPressed()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = bindingFactory(layoutInflater)
        return inflateView()
    }

    private fun inflateView(): View {
        return viewBinding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelState(viewModel)

        onFragmentCreated(view, savedInstanceState)

        Handler().postDelayed({
            isBackPressedEnabled = true
            backPressCallback.isEnabled = shouldHandleOnBackPressed()
        }, BACK_PRESS_DELAY)
    }


    protected open fun setFitSystemWindow(
        insetView: View?,
        insetLeft: Int? = null,
        insetTop: Int? = null,
        insetRight: Int? = null,
        insetBottom: Int? = null
    ) {
        view?.setOnApplyWindowInsetsListener { v, insets ->
            insets.replaceSystemWindowInsets(
                insetLeft ?: insets.systemWindowInsetLeft,
                insetTop ?: insets.systemWindowInsetTop,
                insetRight ?: insets.systemWindowInsetRight,
                insetBottom ?: insets.systemWindowInsetBottom
            )
        }
        insetView?.fitsSystemWindows = true
    }

    // Determine if this fragment should handle manual back press
    open fun shouldHandleOnBackPressed(): Boolean = false

    private fun observeViewModelState(viewModel: BaseViewModel) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewModelState.collect {
                    when (it) {
                        is ViewModelState.Loading -> showProgress(it.progress)
                        is ViewModelState.Idle -> hideProgress(it)
                        is ViewModelState.Error -> {
                            hideProgress(it)
                            displayError(it)
                        }
                    }
                }
            }
        }
    }
    open fun showProgress(progress: ProgressType) {
        when (progress) {
            is ProgressType.ProgressDialog -> {
                progressDialog?.showProgress(childFragmentManager)
            }
            else -> {progressDialog?.showProgress(childFragmentManager)}
        }
        this.currentProgress = progress
    }

    open fun hideProgress(toState: ViewModelState) {
        hideProgressAndRelease()
    }

//    private fun observeNetworkChange() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                networkConnectionManager.networkState
//                    .collectLatestSafely { onNetworkChange(it) }
//            }
//        }
//    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupOnBackPressed()
    }



    override fun onDestroyView() {
        _viewBinding = null
        hideProgressAndRelease()
        super.onDestroyView()
    }

    abstract fun onFragmentCreated(view: View, savedInstanceState: Bundle?)

    open fun displayError(error: ViewModelState.Error) {
        Timber.e("displayError: $error")
        if (!error.shouldNotify) return
        // Show warning dialog for offline and timeout case
        when (error.code) {
            ERROR_CODE_OFFLINE -> {
                showToast(error.error) // Display offline error
            }
            ERROR_CODE_TIMEOUT -> {
                showToast(error.error)  // Display timeout error
            }
            else -> {
                showToast(error.error)
            }
        }
    }

    private fun showToast(error: String) {
        Toast.makeText(requireActivity(),error,Toast.LENGTH_SHORT).show()
    }

    private fun setupOnBackPressed() {
        activity?.onBackPressedDispatcher?.addCallback(this, backPressCallback)
    }

    open fun onBackPressed(navigateUp: Boolean = false) {
        val isNavHostFragmentExisted =
            requireActivity().supportFragmentManager.fragments.any { it is NavHostFragment }
        if (isNavHostFragmentExisted && navigateUp && findNavController().navigateUp()) {
            return
        }
        if (isNavHostFragmentExisted && findNavController().popBackStack()) {
            return
        }

//        if ((requireActivity() as? BaseActivity<*>)?.shouldSupportSharedElementTransition() == true) {
//            requireActivity().finishAfterTransition()
//            return
//        }

        requireActivity().finish()
    }

    private fun hideProgressAndRelease() {
        if (true == progressDialog?.hideProgress())
            progressDialog = null
    }
}