package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * 使用WorkManager
 * 1.定义一个后台任务，实现具体的逻辑
 * 2.配置该后台任务的运行条件和约束信息，并构建后台任务请求
 * 3.将该后台任务请求传给WorkManager的 enqueue中，系统会在合适的时间运行
 */

class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    //1.创建的后台任务，必须继承worker类，实现doWork()方法，执行具体的逻辑，此方法不会运行在主线程中，可以做耗时操作
    override fun doWork(): Result {
        Log.d("SimpleWorker ", " do work in SimpleWorker")
        return Result.success()
    }
}