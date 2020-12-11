package com.haodong.study.wanandroid.ui.login

import android.app.ProgressDialog
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.haodong.study.ktx.ext.toast
import com.haodong.study.mvvmcore.base.BaseVMFragment
import com.haodong.study.wanandroid.R
import com.haodong.study.wanandroid.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.title_layout.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * created by linghaoDo on 2020/12/1
 * description:
 *
 * version:
 */
class LoginFragment :BaseVMFragment<LoginViewModel>(){
    override fun getLayoutResId(): Int {
       return R.layout.activity_login
    }

    override fun initVM(): LoginViewModel {
        return getViewModel()
    }

    override fun initView() {
        (mBinding as ActivityLoginBinding).viewModel=mViewModel;
        mToolbar.setTitle(R.string.login)
        mToolbar.setNavigationIcon(R.drawable.arrow_back)
    }

    override fun initData() {
        mToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    override fun startObserve() {
        mViewModel.apply {

            uiState.observe(viewLifecycleOwner, Observer {
                if (it.isLoading) showProgressDialog()

                it.isSuccess?.let {
                    dismissProgressDialog()
                    findNavController().navigateUp()
                }

                it.isError?.let { err ->
                    dismissProgressDialog()
                    activity?.toast(err)
                }
            })
        }
    }
    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(context)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

}