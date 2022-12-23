package com.example.jetpack

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Entity 注解
 * 将此类声明为实体类
 * Room 主要由Entity、Dao、DataBase三部分组成
 * Entity 。用于定义封装实际数据的实体类，每个实体类都会在数据库中有一张对应的表，并
且表中的列是根据实体类中的字段自动生成的。
Dao 。Dao 是数据访问对象的意思，通常会在这里对数据库的各项操作进行封装，在实际
编程的时候，逻辑层就不需要和底层数据库打交道了，直接和Dao 层进行交互即可。
Database 。用于定义数据库中的关键信息，包括数据库的版本号、包含哪些实体类以及提
供Dao 层的访问实例。
 */

@Entity
data class User(var firstName: String, var lastName: String, var age: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}