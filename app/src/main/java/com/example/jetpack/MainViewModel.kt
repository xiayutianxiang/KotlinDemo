package com.example.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved: Int) : ViewModel() {

    private val _counter = MutableLiveData<Int>()

    private val userLiveData = MutableLiveData<User>()

    private val userIdLiveData = MutableLiveData<String>()

    
    //使用Transformations.switchMap函数将其转换成可观察的liveData对象
    //一旦userIdLiveData的 数据发生变化，那么观察userIdLiveData的switchMap()方法就会执行
    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) { userId ->
        Repository.getUser(userId)
    }

    /**
     * User类中，有姓名、年龄，假设外部只需要姓名这一个属性，那么按往常的写法，会把年龄也传出去
     * 使用Transformations.map，可以对livedata的数据类型进行转换，在转换函数里写具体的逻辑即可
     * 比如下方 将user的firstName、lastName转换成了一个字符串
     */
    val userName: LiveData<String> = Transformations.map(userLiveData) { user ->
        "${user.firstName} ${user.lastName}"
    }

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

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }
}