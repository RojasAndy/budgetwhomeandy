package com.example.budgetwise.ui.holder

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.ColorStateList
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetwise.databinding.ItemCategoryBinding
import com.example.budgetwise.database.entities.CategoryEntity
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.room.Room
import com.example.budgetwise.R
import com.example.budgetwise.database.BudgetwiseDatabase
import com.example.budgetwise.ui.adapter.RecyclerAdapterSpendCategories
import com.example.budgetwise.ui.view.HomeScreenFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoryViewHolder(
    private val binding: ItemCategoryBinding
): RecyclerView.ViewHolder(binding.root){

    val listCate = mutableListOf<CategoryEntity>()

    val adapterFV by lazy { RecyclerAdapterSpendCategories(listCate) }

    private fun setAdapterV(list: List<CategoryEntity>){
        adapterFV.setData(list)
    }

    private val CATEGORY_DATABASE = "category_table"


    fun bind(cat: CategoryEntity){
        with(binding){
            categoryd.text = cat.category
            val drawable = ContextCompat.getColorStateList(circleBackground.context, cat.colorResource)
            circleBackground.imageTintList = drawable
            //circleBackground.setColorFilter(cat.colorResource, PorterDuff.Mode.SRC_IN)
            categorybar.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, cat.colorResource))
            svgImage.setImageResource(cat.icLogoResource)
            categorybar.progress = cat.progress
            spend.text = "\$${cat.progress.toString()} "
            total.text = "of \$${cat.maxProgress.toString()}"
            available.text = "\$${cat.maxProgress - cat.progress} left"

            itemcateg.setOnClickListener{
                val position = adapterPosition + 1
                Log.d("position", "position: $position")

                val editText = EditText(categoryd.context)
                editText.hint = "Enter expense"

                //displaying alertdialog to obtain the input expense made by the user
                AlertDialog.Builder(categoryd.getContext()).
                setTitle("How much did you spend today here?")
                    .setView(editText)
                    .setPositiveButton("Add Expense"){ _, _ ->
                        val input = editText.text.toString()
                        if(input.isNotEmpty()){
                            val expenser = input.toInt()
                            aggregateSpend(categoryd.getContext(), position, expenser)
                            /*CoroutineScope(Dispatchers.IO).launch {
                                val refreshdata = getRoomDataBase(itemcateg.getContext()).getCategorieDao().getAllCats()
                                setAdapterV(refreshdata)
                            }*/
                        }
                        /*CoroutineScope(Dispatchers.IO).launch {
                            val refreshdata = getRoomDataBase(categoryd.getContext()).getCategorieDao().getAllCats()
                            setAdapterV(refreshdata)
                        }*/

                    }.setNegativeButton("Cancel"){ dialog, _ ->
                        dialog.dismiss()
                    }.create().show()


            }

        }

    }

    //Method to update the expenses made by the user in an specific category
    fun aggregateSpend(context:Context, position:Int,  agspend: Int){
        CoroutineScope(Dispatchers.IO).launch {
            //val id = position + 1
            val spend =getRoomDataBase(context).getCategorieDao().getProgress(position)
            val newspend = spend?.plus(agspend)
            if (newspend != null) {
                getRoomDataBase(context).getCategorieDao().updateById(position,newspend)
                /*val refreshdata = getRoomDataBase(context).getCategorieDao().getAllCats()
                setAdapterV(refreshdata)*/
            }
        }
    }


    private fun getRoomDataBase(context: Context): BudgetwiseDatabase {
        return Room.databaseBuilder(
            context,
            BudgetwiseDatabase::class.java,
            CATEGORY_DATABASE
        ).build()
    }


}


