package com.example.jetpack

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.example.BaseActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.ActivityJetBinding

class JetActivity : BaseActivity<ActivityJetBinding>() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    lateinit var observer: MyObserver   //手写一个监听器感知activity生命周期

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observer = MyObserver(lifecycle)

        //添加完此行，就会在对应的生命周期去实现MyObserver中对应的方法
        lifecycle.addObserver(observer)

        sp = getPreferences(MODE_PRIVATE)
        val counterReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(counterReserved)
        )[MainViewModel::class.java]
        refreshCounter()
    }

    override fun onStart() {
        super.onStart()
        //  observer.activityStart()
    }

    override fun initListener() {
        binding.plusOneBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()
        }

        binding.clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
    }

    private fun refreshCounter() {
        binding.infoText.text = viewModel.counter.toString()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_jet
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter)
        }
    }

    override fun onStop() {
        super.onStop()
        //通过在activity的生命周期函数中调用定义的这些方法去感知
        //  observer.activityStop()
    }
}