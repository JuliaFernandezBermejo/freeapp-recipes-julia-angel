package com.example.recipeapp.network


import com.example.recipeapp.common.Recipe
import retrofit2.http.GET
import retrofit2.http.Headers

interface RecipesAPI {
    @Headers("Accept: application/json")
    @GET("recipes")
    suspend fun getRecipes(): List<RecipeResponse>
}