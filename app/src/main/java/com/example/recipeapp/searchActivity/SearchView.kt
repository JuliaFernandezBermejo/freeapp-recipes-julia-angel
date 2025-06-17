package com.example.recipeapp.searchActivity

import com.example.recipeapp.common.Recipe
import com.example.recipeapp.network.RecipeResponse

interface SearchView {
    fun showRecipes(recipes: List<RecipeResponse>)
    fun showError(s: String)


}