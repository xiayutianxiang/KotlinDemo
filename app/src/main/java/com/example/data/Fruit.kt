package com.example.data

import java.io.Serializable

//intent传递对象，可以实现Serializable接口、Parcelable
//这种传递对象的工作原理是先将一个对象序列化成可存储或可传输的状态，传
//递给另外一个Activity 后再将其反序列化成一个新的对象。虽然这两个对象中存储的数据完全一
//致，但是它们实际上是不同的对象
class Fruit(val name:String,val imageId:Int) : Serializable