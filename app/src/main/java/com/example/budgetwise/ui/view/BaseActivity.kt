package com.example.budgetwise.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

//Comments: This BaseActivity works as a constructor to avoid directly inflate the view o each activity by extending an specific activity to this baseactivity

open class BaseActivity<B: ViewBinding>(val bindingFactory: (LayoutInflater) -> B): AppCompatActivity() {
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        setContentView(binding.root)
    }
}