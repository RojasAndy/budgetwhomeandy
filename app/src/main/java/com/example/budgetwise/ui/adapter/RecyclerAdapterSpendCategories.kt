package com.example.budgetwise.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetwise.database.entities.CategoryEntity
import android.view.LayoutInflater
import com.example.budgetwise.ui.holder.CategoryViewHolder
import com.example.budgetwise.databinding.ItemCategoryBinding

class RecyclerAdapterSpendCategories(
    var categorylist: MutableList<CategoryEntity>
): RecyclerView.Adapter<CategoryViewHolder>() {



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val view = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val item = categorylist[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = categorylist.size

    fun setData(data: List<CategoryEntity>){
        categorylist.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}

