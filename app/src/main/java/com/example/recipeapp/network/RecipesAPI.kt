package com.example.recipeapp.network


import com.example.recipeapp.common.Recipe
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface RecipesAPI {
    @Headers("Accept: application/json")
    @GET("recipes")
    suspend fun getRecipes(): Recipe

    @Headers("Accept: application/json")
    @GET("recipes/tag/{tag}")
    suspend fun searchRecipesTag(
        @Path("tag") tag: String,
    ): Recipe

    @Headers("Accept: application/json")
    @GET("recipes/meal-type/{meal-type}")
    suspend fun searchRecipesByMeal(
        @Path("meal-type") mealtype: String,
    ): Recipe


}
