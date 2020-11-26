package com.haodong.study.mvvmcore.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
open class BaseViewModel : ViewModel() {
    open class UiState<T>(
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false,
        val isSuccess: T? = null,
        val isError: String? = null
    )

    open class BaseUiModel<T> {
        var showLoading: Boolean = false
        var showError: String? = null
        var showSuccess: T? = null
        var showEnd: Boolean = false
        var isRefresh: Boolean = false
    }

    val mException: MutableLiveData<Throwable> = MutableLiveData()
    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch { block }
    }
    suspend fun <T> launchOnIO(block:suspend  CoroutineScope.()->T){
        withContext(Dispatchers.IO){
            block
        }
    }

}