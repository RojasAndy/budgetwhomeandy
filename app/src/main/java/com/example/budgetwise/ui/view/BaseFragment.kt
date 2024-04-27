package com.example.budgetwise.ui.view

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

//Comments: This BaseFragment works as a constructor to avoid directly inflate the view o each fragment by extending an specific fragment to this basefragment


abstract class BaseFragment<B: ViewBinding>(val bindingFactory: (LayoutInflater) -> B): Fragment() {
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

}