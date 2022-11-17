package com.example.logic

import androidx.lifecycle.liveData
import com.example.logic.model.Place
import com.example.logic.model.Weather
import com.example.logic.network.SunnyWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetWork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    //发起请求获取天气数据
    fun refreshWeather(lng: String, lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {
                //一次获取到天气数据。因为实时天气和未来天气是通过两个请求返回的，所以他俩不会同时返回，但是又需要等到它们的响应结果后才能继续执行程序
                //所以，用async函数。在两个async函数中发起请求，调用它们的await方法，当这两个网络请求都成功时，再去执行程序。由于async函数必须在协程作用域内才能调用
                //所以又在外边套了一层coroutineScope函数创建一个协程作用域
                val deferredRealtime = async {
                    SunnyWeatherNetWork.getRealTimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    SunnyWeatherNetWork.getDailyWeather(lng, lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                    val weather = Weather(
                        realtimeResponse.result.realtime,
                        dailyResponse.result.daily
                    )
                    Result.success(weather)
                } else {
                    Result.failure(
                        RuntimeException(
                            "realtime response status is ${realtimeResponse.status}" +
                                    "daily response status is ${dailyResponse.status}"
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Result.failure<Weather>(e)
        }
        emit(result)
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}