package com.example.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.BaseActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.ActivityServiceBinding
import kotlin.concurrent.thread

class ServiceActivity : BaseActivity<ActivityServiceBinding>() {

    val updateText = 1
    var num = 1

    lateinit var downloadBinder: MyService.DownloadBinder
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = MyService.DownloadBinder()
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

    }

    override fun initListener() {
        binding.changeTextBtn.setOnClickListener {
            thread {
                for (i in 0..60) {
                    Thread.sleep(1000)
                    val msg = Message.obtain()
                    msg.what = updateText
                    handler.sendMessage(msg)
                }
            }
        }

        binding.bind.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }

        //解绑
        binding.unBind.setOnClickListener {
            unbindService(connection)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_service
    }

    //1.先定义一个handler去重写执行的逻辑
    //2.之后用这个handler去发送消息即可
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateText -> {
                    binding.textView.setText(num.toString())
                    num++
                }
            }
        }
    }
}