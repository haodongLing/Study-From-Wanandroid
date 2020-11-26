package com.haodong.study.wanandroid

import android.app.Application
import android.content.Context
import com.haodong.study.mvvmcore.model.bean.User
import com.haodong.study.mvvmcore.util.Timer
import kotlin.properties.Delegates

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
class App :Application(){
    companion object{
        var CONTEXT:Context by Delegates.notNull()
        lateinit var CURRENT_USER: User
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Timer.start(APP_START)
    }

    override fun onCreate() {
        super.onCreate()
    }
}