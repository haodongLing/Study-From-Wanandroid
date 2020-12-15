package com.haodong.study.wanandroid.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.haodong.study.mvvmcore.Result
import com.haodong.study.mvvmcore.base.BaseViewModel
import com.haodong.study.wanandroid.model.bean.SystemParent
import com.haodong.study.wanandroid.model.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * created by linghaoDo on 2020/12/14
 * description:
 *
 * version:
 */
class ProjectViewModel(private val repository: ProjectRepository) : BaseViewModel() {
    private val _mSystemParentList: MutableLiveData<List<SystemParent>> = MutableLiveData()
    val systemData: LiveData<List<SystemParent>>
        get() = _mSystemParentList

    fun getProjectTypeList() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) { repository.getProjectTypeList() }
            if (result is Result.Success)
                _mSystemParentList.value = result.data
        }
    }

    fun getBlogType() {
        viewModelScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                repository.getBlog()
            }
            if (result is Result.Success) {
                _mSystemParentList.value = result.data
            }
        }
    }
}