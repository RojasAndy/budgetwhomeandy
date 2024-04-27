package com.example.budgetwise.ui.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.example.budgetwise.R
import com.example.budgetwise.database.BudgetwiseDatabase
import com.example.budgetwise.database.entities.CategoryEntity
import com.example.budgetwise.ui.holder.CategoryViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(): ViewModel() {

    private val CATEGORY_DATABASE = "category_table"

    val catVmList = MutableLiveData<List<CategoryEntity>>()

    private val _clickedPosition = MutableLiveData<Int>()
    val clickedPosition: LiveData<Int> = _clickedPosition

    //Method used to populate database
    fun populateTable(context:Context){
        CoroutineScope(Dispatchers.IO).launch {
            val categorydata = getRoomDataBase(context).getCategorieDao().getCount()

            //if function ensures the population is done only once
            if (categorydata == 0 ){
                getRoomDataBase(context).getCategorieDao().insertMany(getCategories())
            }
        }
    }

    //Method to retrieve data elements from our database to our viewmodel list
    fun getMyCategories(context: Context){
        CoroutineScope(Dispatchers.IO).launch {
            val myCategories = getRoomDataBase(context).getCategorieDao().getAllCats()
            catVmList.postValue(myCategories)
        }
    }

    //Method to assign the sum of all the expenses made by the user, to the binding component
    fun assignTotalProgress(context: Context, text: TextView){
        CoroutineScope(Dispatchers.IO).launch {
            text.text = "\$${getRoomDataBase(context).getCategorieDao().sumProgress().toString()}"
        }
    }

    //Method to assign the sum of all the category budgets, to the binding component
    fun assignTotalBudget(context: Context, text: TextView){
        CoroutineScope(Dispatchers.IO).launch {
            text.text = "\$${getRoomDataBase(context).getCategorieDao().sumMax().toString()}"
        }
    }

    //Method to assign the remaining budget to the binding component
    fun remainingBudget(context: Context, text: TextView){
        CoroutineScope(Dispatchers.IO).launch {
            var remaining = getRoomDataBase(context).getCategorieDao().sumMax() - getRoomDataBase(context).getCategorieDao().sumProgress()
            text.text =  "\$${remaining.toString()}"
        }
    }

    //Method to assign the progress bar value and the max category budget bar value, to the bar component
    fun progressDraw(context: Context, progres: ProgressBar){
        CoroutineScope(Dispatchers.IO).launch {
            progres.progress = getRoomDataBase(context).getCategorieDao().sumProgress()
            progres.max = getRoomDataBase(context).getCategorieDao().sumMax()
        }
    }

    private fun getCategories(): List<CategoryEntity> {
        val categories = mutableListOf<CategoryEntity>()
        categories.add(CategoryEntity(1, "Food",10,100, R.drawable.food, R.color.blue_100))
        categories.add(CategoryEntity(2, "Shopping",50,100, R.drawable.shopping, R.color.blue_200))
        categories.add(CategoryEntity(3, "Transportation",20,100, R.drawable.transportation, R.color.orange_100))
        categories.add(CategoryEntity(4, "Education",40,100, R.drawable.education, R.color.green_900))
        categories.add(CategoryEntity(5, "Groceries",130,200, R.drawable.food, R.color.green_100))
        categories.add(CategoryEntity(6, "Housing",5654,5654, R.drawable.housing, R.color.red_100))
        categories.add(CategoryEntity(7, "Gym",100,100, R.drawable.gym, R.color.purple_200))
        categories.add(CategoryEntity(8, "Electricity",200,500, R.drawable.electricity, R.color.pink_100))
        categories.add(CategoryEntity(9, "Garden",40,180, R.drawable.garden, R.color.green_100))
        categories.add(CategoryEntity(10, "Streaming Shows",60,120, R.drawable.streaming, R.color.pink_200))
        return categories
    }

    private fun getRoomDataBase(context: Context): BudgetwiseDatabase {
        return Room.databaseBuilder(
            context,
            BudgetwiseDatabase::class.java,
            CATEGORY_DATABASE
        ).build()
    }
}