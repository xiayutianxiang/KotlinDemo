package com.example.kotlindemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.kotlindemo.databinding.LeftFragmentBinding

class LeftFragment :Fragment(){

    private lateinit var binding: LeftFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LeftFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }
}