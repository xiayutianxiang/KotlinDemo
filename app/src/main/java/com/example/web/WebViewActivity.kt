package com.example.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.BaseActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.ActivityWebViewBinding

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override fun initListener() {

        binding.webView.apply {
            true.also { settings.javaScriptEnabled = it }
            webViewClient = WebViewClient()
            loadUrl("https//www.baidu.com")
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }
}