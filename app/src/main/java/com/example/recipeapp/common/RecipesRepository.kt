package com.example.recipeapp.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.example.recipeapp.network.RecipesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RecipesRepository {

    private val api: RecipesAPI

    init {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        api = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RecipesAPI::class.java)
    }

    suspend fun getFavourites() =
        try {
            withContext(Dispatchers.IO) {
                val recipes = api.getRecipes().recipes
                val filteredRecipes = recipes.filter { it.id in setOf(1,3,4,7,8) }
                Result.success(filteredRecipes)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
}