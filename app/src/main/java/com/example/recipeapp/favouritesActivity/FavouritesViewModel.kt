package com.example.recipeapp.favouritesActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.common.Recipe
import com.example.recipeapp.common.RecipesRepository
import com.example.recipeapp.network.RecipeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel : ViewModel(){

    private val recipesRepository = RecipesRepository()

    init {
        loadFavourites()
    }

    var view: FavouritesView? = null
        set(value){
            field = value
            updateView()
        }

    private fun loadFavourites() {
        viewModelScope.launch {
            recipesRepository.getFavourites()
                .onSuccess {
                    view?.apply{showFavourites(it)}
                }
                .onFailure {
                    view?.showError("Error loading news")
                }
        }
    }

    fun updateView(){

    }



}