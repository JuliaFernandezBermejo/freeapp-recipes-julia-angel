package com.example.recipeapp.favouritesActivity

import com.example.recipeapp.network.RecipeResponse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipeapp.databinding.ItemFavouritesBinding

class FavouritesAdapter (private val favourites: List<RecipeResponse>) : RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {

    class FavouritesViewHolder(val binding: ItemFavouritesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val binding = ItemFavouritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val recipe = favourites[position]
        holder.binding.textViewFavourites.text = recipe.name
        Glide.with(holder.binding.imageViewFavourites)
            .load(recipe.image)
            //.circleCrop()
            .into(holder.binding.imageViewFavourites)
    }

    override fun getItemCount(): Int = favourites.size
}