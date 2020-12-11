package com.haodong.study.wanandroid

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * created by linghaoDo on 2020/11/29
 * description:
 *
 * version:
 */
data class CoroutinesDispatcherProvider @Inject constructor(
    val main: CoroutineDispatcher = Dispatchers.Main,
    val computation: CoroutineDispatcher = Dispatchers.Default,
    val io: CoroutineDispatcher = Dispatchers.IO
)