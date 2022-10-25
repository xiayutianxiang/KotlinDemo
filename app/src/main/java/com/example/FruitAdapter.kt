package com.example

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Fruit
import com.example.kotlindemo.databinding.ListItemBinding

class FruitAdapter(val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.InnerHolder>() {

    private val TAG = "FruitAdapter"
    private var m = 1
    private var n = 1
    inner class InnerHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val fruitImage: ImageView = binding.fruitImage
        val fruitName: TextView = binding.fruitName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitAdapter.InnerHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        Log.d(TAG,"onCreateViewHolder 执行了---> "+ m++ +"次")
        return InnerHolder(binding)
    }

    override fun onBindViewHolder(holder: FruitAdapter.InnerHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitImage.setImageResource(fruit.imageId)
        holder.fruitName.text = fruit.name
        Log.d(TAG,"onBindViewHolder 执行了---> "+ n++ +"次")
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }
}