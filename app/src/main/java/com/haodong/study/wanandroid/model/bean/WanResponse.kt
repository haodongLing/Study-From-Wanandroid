package com.haodong.study.wanandroid.model.bean

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)