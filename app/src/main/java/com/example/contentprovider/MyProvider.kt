package com.example.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri

class MyProvider : ContentProvider() {

    private val table1Dir = 0
    private val table1Item = 1
    private val table2Dir = 2
    private val table2Item = 3

    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        uriMatcher.addURI("com.example.contentprovider", "table1", table1Dir)
        uriMatcher.addURI("com.example.contentprovider", "table1/#", table1Item)
        uriMatcher.addURI("com.example.contentprovider", "table2", table2Dir)
        uriMatcher.addURI("com.example.contentprovider", "table2/#", table2Item)
    }

    //初始化ContentProvider时调用，完成对数据库的创建和升级
    override fun onCreate(): Boolean {
        return false
    }

    //查询
    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        when (uriMatcher.match(uri)) {
            table1Dir -> {

            }
            table1Item -> {

            }
            table2Dir -> {

            }
            table2Item -> {

            }
        }
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}