package com.haodong.study.wanandroid.ui.login

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haodong.study.mvvmcore.base.BaseViewModel
import com.haodong.study.wanandroid.CoroutinesDispatcherProvider
import com.haodong.study.wanandroid.checkResult
import com.haodong.study.wanandroid.model.bean.User
import com.haodong.study.wanandroid.model.repository.LoginRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
class LoginViewModel @ViewModelInject constructor(
    val repository: LoginRepository,
    val provider: CoroutinesDispatcherProvider
) : BaseViewModel() {
    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")
    private val _uiState = MutableLiveData<LoginUiState<User>>()
    val uiState: LiveData<LoginUiState<User>>
        get() = _uiState

    private fun isInputValid(userName: String, passWord: String) =
        userName.isNotBlank() && passWord.isNotBlank()

    fun loginDataChanged() {
        _uiState.value = LoginUiState(
            enableLoginButton = isInputValid(
                userName.get() ?: " ",
                passWord = passWord.get() ?: " "
            )
        )
    }

    fun login() {
        launchOnUI {
            if (userName.get().isNullOrBlank() || passWord.get().isNullOrBlank()) {
                _uiState.value = LoginUiState(enableLoginButton = false)
                return@launchOnUI
            }
            _uiState.value = LoginUiState(isLoading = true)
            val result = repository.login(userName.get() ?: " ", passWord.get() ?: " ")
            result.checkResult(
                onSuccess = {
                    _uiState.value = LoginUiState(isSuccess = it, enableLoginButton = true)

                }, onError = {
                    _uiState.value = LoginUiState(isError = it, enableLoginButton = true)
                })
        }
    }

    fun register() {
        viewModelScope.launch(provider.computation) {
            if (userName.get().isNullOrBlank() || passWord.get().isNullOrBlank())
                return@launch
            withContext(provider.main) {
                _uiState.value = LoginUiState(isLoading = true)
            }
            val result = repository.register(userName.get() ?: " ", passWord.get() ?: " ")
            result.checkResult({
                _uiState.value = LoginUiState(isSuccess = it, enableLoginButton = true)

            }, { _uiState.value = LoginUiState(isError = it, enableLoginButton = true) })
        }

    }
    val verifyInput: (String) -> Unit = { loginDataChanged() }

}