package com.haodong.study.wanandroid.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haodong.study.mvvmcore.base.BaseViewModel
import com.haodong.study.wanandroid.checkSuccess
import com.haodong.study.wanandroid.model.bean.Navigation
import com.haodong.study.wanandroid.model.repository.NavigationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * created by linghaoDo on 2020/12/10
 * description:
 *
 * version:
 */
class NavigationViewModel(private val navigationRepository: NavigationRepository) :
    BaseViewModel() {
    private val _uiState = MutableLiveData<List<Navigation>>()
    val uiState: LiveData<List<Navigation>>
        get() {
            return _uiState
        }
    fun getNavigation(){
        launchOnUI {
            val result= withContext(Dispatchers.IO){
                navigationRepository.getNavigation()
            }
            result.checkSuccess {
                _uiState.value=it
            }
        }
    }

}