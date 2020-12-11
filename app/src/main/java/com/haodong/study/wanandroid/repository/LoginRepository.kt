package com.haodong.study.wanandroid.repository

import com.google.gson.Gson
import com.haodong.study.wanandroid.App
import com.haodong.study.wanandroid.R
import com.haodong.study.wanandroid.model.api.BaseRepository
import com.haodong.study.wanandroid.model.api.WanService
import com.haodong.study.wanandroid.model.bean.User
import luyao.wanandroid.util.Preference
import javax.inject.Inject
import com.haodong.study.wanandroid.Result

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
class LoginRepository @Inject constructor(val service: WanService) : BaseRepository() {
    private var isLogin by Preference(Preference.IS_LOGIN, false)
    private var userJson by Preference(Preference.USER_GSON, "")
    suspend fun login(userName: String, password: String): Result<User> {

        return safeApiCall(
            call = { requestLogin(userName, password) }, errorMessage = App.CONTEXT.getString(
                R.string.about
            )
        )
    }

    private suspend fun requestLogin(userName: String, password: String): Result<User> {
        val response = service.login(userName, password)
        return executeResponse(response, {
            val user = response.data
            isLogin = true;
            userJson = Gson().toJson(user)
            App.CURRENT_USER = user;
        })

    }

    suspend fun register(userName: String, password: String): Result<User> {
        return safeApiCall(call = { requestRegister(userName, password) }, errorMessage = "注册失败")
    }

    private suspend fun requestRegister(userName: String, password: String): Result<User> {
        val response = service.register(userName, password, password)
        return executeResponse(response, { requestLogin(userName, password) })
    }

}