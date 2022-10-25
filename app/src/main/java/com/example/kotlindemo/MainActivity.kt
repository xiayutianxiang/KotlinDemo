package com.example.kotlindemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.FruitAdapter
import com.example.data.FruitRvData
import com.example.data.ListViewData
import com.example.kotlindemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.showToast.setOnClickListener {
            val intent = Intent("com.example.kotlindemo.ACTION_START")
            val data = "hello SecondActivity"
            intent.putExtra("extra_data", data)
            startActivityForResult(intent, 1)
        }
        initListView()
        initRvView()
    }

    private fun initRvView() {
        val adapter = FruitAdapter(FruitRvData.initFruits())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun initListView() {
        val adapter = ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ListViewData.data)
        binding.listView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_item ->
                Toast.makeText(this, "点击了添加", Toast.LENGTH_SHORT).show()
            R.id.remove_item ->
                Toast.makeText(this, "点击了移除", Toast.LENGTH_SHORT).show()
        }

        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            1 ->
                if (requestCode == RESULT_OK) {
                    val returnData = data?.getStringExtra("data_return")
                    if (returnData != null) {
                        Log.d(TAG, returnData)
                    }
                }
        }
    }
}