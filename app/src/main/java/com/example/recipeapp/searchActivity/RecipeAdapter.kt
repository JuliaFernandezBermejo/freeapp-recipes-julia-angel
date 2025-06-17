package com.example.recipeapp.searchActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.R
import com.example.recipeapp.network.RecipeResponse

class RecipeAdapter(private var items: List<RecipeResponse>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewRecipes: TextView = view.findViewById(R.id.textViewRecipes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cosmetic = items[position]
        holder.textViewRecipes.text = cosmetic.name
    }

    fun updateData(newItems: List<RecipeResponse>) {
        items = newItems
        notifyDataSetChanged()
    }
}
