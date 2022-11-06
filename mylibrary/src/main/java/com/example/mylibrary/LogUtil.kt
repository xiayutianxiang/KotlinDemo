package com.example.mylibrary

import android.util.Log

object LogUtil {

    private val isDebug = true

    fun d(tag: String, msg: String) {
        if (isDebug) {
            Log.d(tag, msg)
        }
    }
}