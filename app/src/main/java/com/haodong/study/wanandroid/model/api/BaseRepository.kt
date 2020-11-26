package com.haodong.study.wanandroid.model.api

import com.haodong.study.ktx.ext.executeCmd
import com.haodong.study.wanandroid.model.bean.WanResponse
import com.haodong.study.wanandroid.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
open class BaseRepository {
    suspend fun <T : Any> apiCall(call: suspend () -> WanResponse<T>): WanResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Result<T>,
        errorMessage: String
    ): Result<T> {
        return try {
            call()
        } catch (exception: Exception) {
            Result.Error(exception);
        }
    }

    suspend fun <T : Any> executeResponse(
        response: WanResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
        errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): Result<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                Result.Error(IOException(response.errorMsg))
            } else {
                successBlock?.let {
                    it()
                }
                Result.Success(response.data)
            }
        }
    }
}