package com.example.recipeapp.favouritesActivity

import com.example.recipeapp.network.RecipeResponse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.ItemFavouritesBinding

class FavouritesAdapter (private val favourites: List<RecipeResponse>) : RecyclerView.Adapter<FavouritesAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(val binding: ItemFavouritesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = ItemFavouritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = favourites[position]
        holder.binding.textViewFavourites.text = recipe.name
        Glide.with(holder.binding.imageViewFavourites)
            .load(recipe.image)
            .into(holder.binding.imageViewFavourites)
    }

    override fun getItemCount() = favourites.size
}