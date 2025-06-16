package com.example.recipeapp.favouritesActivity

import com.example.recipeapp.common.Recipe
import com.example.recipeapp.network.RecipeResponse

interface FavouritesView {
    fun showFavourites(favourites: ArrayList<RecipeResponse>)
    //fun showError (string: String)

}