package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.broadcast.ActivityCollector
import com.example.database.open

abstract class BaseActivity<V : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        initListener()
        /*getSharedPreferences("data", MODE_PRIVATE).open{
            putString("name","tom")
        }*/
        ActivityCollector.addActivity(this)
    }

    abstract fun initListener()

    abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}