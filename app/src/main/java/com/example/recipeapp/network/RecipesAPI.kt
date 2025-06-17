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
    @GET("recipes/search/all")
    suspend fun searchRecipesByName(
        @Query("name") name: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): RecipeSearchResponse

    @Headers("Accept: application/json")
    @GET("recipes/search/all")
    suspend fun searchRecipesByDifficulty(
        @Query("difficulty") difficulty: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): RecipeSearchResponse

    @Headers("Accept: application/json")
    @GET("recipes/search/all")
    suspend fun searchRecipesByCuisin(
        @Query("cuisin") cuisin: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): RecipeSearchResponse

    @Headers("Accept: application/json")
    @GET("recipes/search/all")
    suspend fun searchRecipesByServings(
        @Query("servings") servings: String,
        @Query("matchMethod") matchMethod: String = "contains"
    ): RecipeSearchResponse
}
