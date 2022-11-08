package com.example.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.BaseActivity
import com.example.kotlindemo.R
import com.example.kotlindemo.databinding.ActivityWebViewBinding
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override fun initListener() {

        /*binding.webView.apply {
            true.also { settings.javaScriptEnabled = it }
            webViewClient = WebViewClient()
            loadUrl("https//www.baidu.com")
        }*/
        binding.sendRequestBtn.setOnClickListener {
            // sendRequestWithHttpURLConnection()
            sendRequestWithOkhttp()
        }

        binding.getAppDataBtn.setOnClickListener {
            getRequestWithRetrofit()
        }
    }

    //通过retrofit进行请求的例子
    private fun getRequestWithRetrofit() {

        //1.创建retrofit 通过Retrofit.Builder(),指定根路径
        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //2.创建之前定义的请求
        val appService = retrofit.create(AppService::class.java)
        appService.getAppData().enqueue(object : retrofit2.Callback<List<App>> {
            override fun onResponse(
                call: retrofit2.Call<List<App>>,
                response: retrofit2.Response<List<App>>
            ) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: retrofit2.Call<List<App>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun sendRequestWithOkhttp() {
        //1.构建client
        val okhttpClient = OkHttpClient()

        //2.构建请求
        val request = Request.Builder()
            .url("https://www.baidu.com").build()

        //3.构建call
        val call = okhttpClient.newCall(request)

        //4.发起请求,同步、异步
        //call.execute()
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //
            }

            override fun onResponse(call: Call, response: Response) {
                //
                response.body?.string()?.let { showResponse(it) }
            }
        })
    }

    private fun sendRequestWithHttpURLConnection() {
        //发起网络请求，耗时操作，在线程内
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection?
                connection?.connectTimeout = 8000
                connection?.readTimeout = 8000

                val input = connection?.inputStream
                //对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))

                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }

                showResponse(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                //最后关闭
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            binding.responseText.text = response
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_web_view
    }
}