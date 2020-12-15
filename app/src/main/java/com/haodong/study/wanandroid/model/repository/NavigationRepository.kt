package com.haodong.study.wanandroid.model.repository

import com.haodong.study.wanandroid.model.api.BaseRepository
import com.haodong.study.wanandroid.model.api.WanRetrofitClient
import com.haodong.study.wanandroid.model.bean.Navigation
import com.haodong.study.mvvmcore.Result

/**
 * created by linghaoDo on 2020/12/10
 * description:
 *
 * version:
 */
class NavigationRepository : BaseRepository() {
    suspend fun getNavigation(): Result<List<Navigation>> {
        return safeApiCall(call={requestNavigation()}, errorMessage = "获取数据失败")
    }

    private suspend fun requestNavigation(): Result<List<Navigation>> =
        executeResponse(response=WanRetrofitClient.service.getNavigation())

}