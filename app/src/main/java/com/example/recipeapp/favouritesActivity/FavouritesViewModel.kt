package com.example.recipeapp.favouritesActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.common.Recipe
import com.example.recipeapp.common.RecipesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouritesViewModel : ViewModel(){

    init {
        //loadFavourites()
    }

    var imageURL: String? = null
    val favourites = ArrayList<Recipe>()
    private val RecipesRepository= RecipesRepository()

    var view: FavouritesView? = null
        set(value){
            field = value
            updateView()
        }

    /*private fun loadFavourites() {
        viewModelScope.launch(Dispatchers.Main) {
            RecipesRepository.getFavourites()
                .onSuccess {
                    imageURL = it.first
                    favourites.clear()
                    favourites.addAll(it.second)
                    showNews()
                }
                .onFailure {
                    view?.showError("Error loading news")
                }
        }
    }

    fun showFavourites() =
        view?.apply{
            //showImage(imageURL)
            //showFavourites(favourites)
        }*/
    fun updateView(){

    }



}