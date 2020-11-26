package com.haodong.study.wanandroid

import java.lang.Exception

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
sealed class Result <out T:Any>{
    data class Success<out T :Any>(val data:T): Result<T>()
    data class Error(val exception: Exception): Result<Nothing>()
    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}