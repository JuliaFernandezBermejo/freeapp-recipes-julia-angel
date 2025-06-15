package com.example.recipeapp.searchActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.common.Recipe
import com.example.recipeapp.common.RecipesRepository
import com.example.recipeapp.favouritesActivity.FavouritesView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel: ViewModel() {
    var view: SearchView? = null
        set(value){
            field = value
            updateView()
        }
    fun updateView(){

    }
}