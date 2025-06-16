package com.example.recipeapp.network


import retrofit2.http.GET
import retrofit2.http.Headers

interface RecipesAPI {
    @Headers("Accept: application/json")
    @GET("news/br")
    suspend fun getFavourites(): FavResponse
}