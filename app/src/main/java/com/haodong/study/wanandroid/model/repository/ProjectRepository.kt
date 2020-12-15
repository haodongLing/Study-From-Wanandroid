package com.haodong.study.wanandroid.model.repository

import com.haodong.study.mvvmcore.Result
import com.haodong.study.wanandroid.model.api.BaseRepository
import com.haodong.study.wanandroid.model.api.WanRetrofitClient
import com.haodong.study.wanandroid.model.bean.ArticleList
import com.haodong.study.wanandroid.model.bean.SystemParent

/**
 * created by linghaoDo on 2020/12/14
 * description:
 *
 * version:
 */
class ProjectRepository : BaseRepository() {
    suspend fun getProjectTypeDetailList(page: Int, cid: Int): Result<ArticleList> {
        return safeApiCall(
            call = { requestProjectTypeDetailList(page, cid) },
            errorMessage = "发生未知错误"
        )
    }

    suspend fun getLastedProject(page: Int): Result<ArticleList> {
        return safeApiCall(call = { requestLastedProject(page) }, errorMessage = "发生未知错误")
    }

    suspend fun getProjectTypeList(): Result<List<SystemParent>> {
        return safeApiCall(call = { requestProjectTypeList() }, errorMessage = "网络错误")
    }

    suspend fun getBlog(): Result<List<SystemParent>> {
        return safeApiCall(call = { requestBlogTypeList() }, errorMessage = "网络错误")
    }

    private suspend fun requestProjectTypeDetailList(page: Int, cid: Int) =
        executeResponse(WanRetrofitClient.service.getProjectTypeDetail(page, cid))

    private suspend fun requestLastedProject(page: Int): Result<ArticleList> =
        executeResponse(WanRetrofitClient.service.getLastedProject(page))

    private suspend fun requestProjectTypeList() =
        executeResponse(WanRetrofitClient.service.getProjectType())

    private suspend fun requestBlogTypeList() =
        executeResponse(WanRetrofitClient.service.getBlogType())

}