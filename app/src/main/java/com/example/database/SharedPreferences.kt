package com.example.database

import android.content.SharedPreferences


/**
 * 首先是扩展函数，为SharedPreferences添加了一个open函数（定义在SharedPreferences.Editor）
 * open函数接收一个SharedPreferences.Editor的函数类型对象
 * 调用editor.block()对函数类型进行调用
 * editor.apply()来提交最后的结果
 *
 * 相当于通过open打开了一个editor对象，在函数参数类型中去进行操作
 *
 * getSharedPreferences("data", MODE_PRIVATE).open{
        putString("name","tom")
    }
 */

fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}