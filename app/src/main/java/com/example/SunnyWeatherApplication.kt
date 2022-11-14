package com.example

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context : Context  //获取全局的context

        const val TOKEN = "2EA3gpeGMNctqQP9"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}