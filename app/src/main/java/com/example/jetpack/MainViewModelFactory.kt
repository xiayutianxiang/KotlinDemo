package com.example.jetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/*
    通过viewmodelprovider 得到的实例，没有给它传递参数的功能
    如何向某个viewmodel构造函数中传递参数，就用到了viewmodel.factory
    向viewmodel 的构造函数中传递参数的方法
    新建一个类，设置它的参数，然后继承viewmodel.factory即可
 */
@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val countReserved: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}