package com.haodong.study.mvvmcore.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * created by linghaoDo on 2020/11/25
 * description:
 *
 * version:
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView();
        initData();
    }

    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun initData()

}