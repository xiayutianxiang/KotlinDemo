package com.example.data

import com.example.kotlindemo.R

object FruitRvData {

    private val fruitList = ArrayList<Fruit>()

    fun initFruits():List<Fruit>{
        repeat(6){
            fruitList.add(Fruit("Apple", R.drawable.ic_launcher_background))
            fruitList.add(Fruit("Banana", R.drawable.ic_launcher_background))
            fruitList.add(Fruit("Pear", R.drawable.ic_launcher_background))
            fruitList.add(Fruit("Orange", R.drawable.ic_launcher_background))
            fruitList.add(Fruit("Grape", R.drawable.ic_launcher_background))
            fruitList.add(Fruit("Pineapple", R.drawable.ic_launcher_background))
            fruitList.add(Fruit("Mango", R.drawable.ic_launcher_background))
        }

        return fruitList
    }
}