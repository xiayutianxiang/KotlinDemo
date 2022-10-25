package com.example

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.kotlindemo.databinding.ActivityFilePersistenceBinding
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.lang.StringBuilder

class FilePersistenceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFilePersistenceBinding
    private val TAG = "FilePersistenceActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilePersistenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val input = load()
        if (input.isNotEmpty()) {
            binding.editText.apply {
                setText(input)
                setSelection(input.length)
            }
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show()
        }
        initListener()
    }

    private fun initListener() {
        binding.saveButton.setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.apply {
                putString("name", "二狗")
                putBoolean("married", false)
                putInt("age", 17)
            }
            editor.apply()
        }

        binding.restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d(TAG, "name ---> $name, age ---> $age, married ---> $married")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val inputText = binding.editText.text.toString()
        save(inputText)
    }

    private fun save(inputText: String) {
        try {
            val outPut = openFileOutput("data", MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(outPut))
            writer.use {
                it.write(inputText)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        binding.editText.setText("")
    }

    private fun load(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return content.toString()
    }
}