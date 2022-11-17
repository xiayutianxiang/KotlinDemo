package com.example.logic.network

import com.example.SunnyWeatherApplication
import com.example.logic.model.DailyResponse
import com.example.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    //这种请求中有参数的，可以使用{} 符号括进去，在方法中使用@Path注解参数
    //意思是声明一个getRealtimeWeather()方法，返回RealTimeResponse，参数是lng、lat（经纬度坐标），发起请求时会将参数自动添加到请求中
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<RealtimeResponse>

    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String):
            Call<DailyResponse>

}