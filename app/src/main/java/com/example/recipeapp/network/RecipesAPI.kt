package com.example.recipeapp.network


import com.example.recipeapp.common.Recipe
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipesAPI {
    @Headers("Accept: application/json")
    @GET("recipes")
    suspend fun getRecipes(): Recipe

    @Headers("Accept: application/json")
    @GET("recipes/search")
    suspend fun searchRecipesByName(
        @Query("name") name: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): Recipe

    @Headers("Accept: application/json")
    @GET("recipes/search")
    suspend fun searchRecipesByDifficulty(
        @Query("difficulty") difficulty: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): Recipe

    @Headers("Accept: application/json")
    @GET("recipes/search")
    suspend fun searchRecipesByCuisine(
        @Query("cuisine") cuisine: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): Recipe

    @Headers("Accept: application/json")
    @GET("recipes/search")
    suspend fun searchRecipesByServings(
        @Query("servings") servings: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): Recipe
}
