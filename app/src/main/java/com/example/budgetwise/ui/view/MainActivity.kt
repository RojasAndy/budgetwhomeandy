package com.example.budgetwise.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetwise.databinding.ActivityMainBinding
import com.example.budgetwise.databinding.HomeScreenBinding
import com.example.budgetwise.ui.viewmodel.CategoryViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("start","start")
        val navView: BottomNavigationView = binding.navView
        Log.d("bottomnavigation assigned","bottomnavigation assigned")
        val navController = findNavController(com.example.budgetwise.R.id.nav_host_fragment)
        Log.d("navcontroller assigned","navcontroller assigned")
        navView.setupWithNavController(navController)
        Log.d("navview stablished","navview stablished")

    }
}

