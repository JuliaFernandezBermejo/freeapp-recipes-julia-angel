package com.example.recipeapp.favouritesActivity

import com.example.recipeapp.common.Recipe

interface FavouritesView {
    fun showFavourites(news: ArrayList<Recipe>)
    //fun showError (string: String)

}