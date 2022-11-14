package com.example.ui.place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.ActivitySunnyWeatherBinding

class SunnyWeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySunnyWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sunny_weather)
    }
}