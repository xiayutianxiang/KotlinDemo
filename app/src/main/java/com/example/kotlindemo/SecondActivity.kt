package com.example.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlindemo.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var secondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)

        secondBinding.button2.setOnClickListener {
            val intent = Intent()
            intent.putExtra("data_return","hello firstActivity")
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}