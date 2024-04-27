package com.example.budgetwise.ui.view

import android.os.Bundle
import android.util.Log
import com.example.budgetwise.databinding.HomeScreenBinding
import com.example.budgetwise.ui.viewmodel.CategoryViewModel
import androidx.fragment.app.viewModels
import com.example.budgetwise.ui.adapter.RecyclerAdapterSpendCategories
import com.example.budgetwise.database.entities.CategoryEntity
import com.example.budgetwise.ui.holder.CategoryViewHolder


class HomeScreenFragment: BaseFragment<HomeScreenBinding>(HomeScreenBinding::inflate) {
    private val catviewmodel: CategoryViewModel by viewModels()

    var listCategories = mutableListOf<CategoryEntity>()

    val adapterSpend by lazy { RecyclerAdapterSpendCategories(listCategories) }

    private val CATEGORY_DATABASE = "category_table"

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)

        //Calling viewmodel functions/methods
        catviewmodel.populateTable(requireContext())
        catviewmodel.getMyCategories(requireContext())

        //Assigning adapter
        binding.recyclerv.adapter = adapterSpend

        //Obtaining the value of the list catVmList from our viewmodel catviewmodel and passing it to the list from this fragment with addAll function
        catviewmodel.catVmList.observe(this){
            listCategories.addAll(it)
            //Notifying our adapter
            adapterSpend.notifyDataSetChanged()
        }

        //Calling viewmodel functions/methods and using binding components as input parameter
        catviewmodel.assignTotalProgress(requireContext(), binding.totalexpense)
        catviewmodel.assignTotalBudget(requireContext(), binding.totalbudget)
        catviewmodel.remainingBudget(requireContext(), binding.budgetnumber)
        catviewmodel.progressDraw(requireContext(), binding.pBar)
    }
}