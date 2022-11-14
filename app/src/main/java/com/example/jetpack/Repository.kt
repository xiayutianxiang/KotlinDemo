package com.example.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    fun getUser(userId:String) : LiveData<User>{

        val liveData = MutableLiveData<User>()

        liveData.value = User(userId,userId,0)
        return liveData
    }
}