package com.example.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder


class MyService : Service() {

    private val mBinder = DownloadBinder()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    class DownloadBinder : Binder() {

        fun startDownload() {
           // LogUtil.d("MyService", "start download executed")
        }

        fun getProgress(): Int {
//            LogUtil.d("MyService", "getProgress executed")

            return 0
        }
    }
}