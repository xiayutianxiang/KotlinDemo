package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.broadcast.ActivityCollector

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}