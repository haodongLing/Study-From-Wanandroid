package com.haodong.study.wanandroid.ui.login

import android.app.ProgressDialog
import androidx.activity.viewModels
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import com.haodong.study.ktx.ext.toast
import com.haodong.study.mvvmcore.base.BaseVMActivity
import com.haodong.study.wanandroid.R
import com.haodong.study.wanandroid.model.bean.Title
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * created by linghaoDo on 2020/11/29
 * description:
 *
 * version:
 */
class LoginActivity : BaseVMActivity<LoginViewModel>() {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun initVM(): LoginViewModel = getViewModel()
    override fun getLayoutResId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
//        mBinding.run {
//            setVariable(BR.viewModel, loginViewModel)
//            setVariable(BR.title, Title(R.string.login, R.drawable.arrow_back) { onBackPressed() })
//        }

    }

    override fun initData() {
    }

    override fun startObserve() {
        loginViewModel.apply {
            uiState.observe(this@LoginActivity, Observer { it ->
                if (it.isLoading) showProgressDialog()

                it.isSuccess?.let {
                    dismissProgressDialog()
                    finish()
                }
                it.isError?.let { err ->
                    dismissProgressDialog()
                    toast(err)
                }

            })
        }
    }

    private var progressDialog: ProgressDialog? = null
    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }

}