package com.example.jetpack

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent


//
class MyObserver(val lifecycle: Lifecycle) : LifecycleObserver {

    private val TAG = "MyObserver"

    //通过继承LifecycleObserver，为方法添加OnLifecycleEvent注解，实现对应的事件
    //使用的时候，在对饮的activity中去注册即可

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d(TAG, "activity start")
        Log.d(TAG, lifecycle.currentState.toString())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d(TAG, "activity stop")
        Log.d(TAG, lifecycle.currentState.toString())
    }
}