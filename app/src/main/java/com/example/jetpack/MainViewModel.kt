package com.example.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved: Int) : ViewModel() {

    private val _counter = MutableLiveData<Int>()

    val counter: LiveData<Int>
        get() = _counter

    init {
        //可以写一些主构造函数的逻辑
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}