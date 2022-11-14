package com.example.data

import android.os.Parcel
import android.os.Parcelable

class Person() : Parcelable {

    var name = ""
    var age = 0

    constructor(parcel: Parcel) : this() {
    }

    override fun describeContents(): Int {
        return 0
    }

    //序列化实现Parcelable时，需要在这个方法下将字段一一写出
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeInt(age)
    }

    //在这里，返回一个对象
    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            val person = Person()
            person.name = parcel.readString() ?: ""
            person.age = parcel.readInt()
            return person
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}