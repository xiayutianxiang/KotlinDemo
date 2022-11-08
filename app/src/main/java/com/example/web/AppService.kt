package com.example.web

import retrofit2.Call
import retrofit2.http.GET

interface AppService {

    /**
     * 添加get注解，表示这是一条get请求，请求地址就是在后边传入的参数，只需要传入相对路径就可以
     *  根路径之后会设置
     */

    @GET("get_data.json")
    fun getAppData() : Call<List<App>>
}