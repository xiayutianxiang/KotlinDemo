package com.example.room

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.jetpack.User

@Database(version = 1, entities = [User::class])
abstract class AppDatabase : RoomDatabase() {

    /**
     * 这里需要做好三部分内容
     * 数据库的版本号、包含那些实体类、提供Dao层的访问实例
     */

    //声明抽象方法，返回UserDao
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            //通过Room.databaseBuilder来构建一个database的实例，其中context要使用application的实例
            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database").build()
                .apply {
                    instance = this
                }
        }
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

}