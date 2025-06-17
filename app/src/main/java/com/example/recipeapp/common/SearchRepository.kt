package com.example.recipeapp.common

import com.example.recipeapp.network.RecipeResponse
import com.example.recipeapp.network.RecipesAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class SearchRepository {
    private val api: RecipesAPI

    init {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        api = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RecipesAPI::class.java)
    }

    suspend fun searchByName(name: String): List<RecipeResponse> {
        val response = api.searchRecipesByName(name)
        return response.recipes.map { data ->
            RecipeResponse(
                id = data.id ?: -1,
                name = data.name ?: "Unknown",
                ingredients = data.ingredients ?: arrayOf("unknown"),
                instructions = data.instructions ?: arrayOf("unknown"),
                prepTimeMinutes = data.prepTimeMinutes ?: -1,
                cookTimeMinutes = data.cookTimeMinutes ?: -1,
                servings = data.servings ?: -1,
                difficulty = data.difficulty ?: "Unknown",
                cuisine = data.cuisine ?: "Unknown",
                image = data.image ?: "Unknown"
            )
        }
    }
    suspend fun searchByServings(name: String): List<RecipeResponse> {
        val response = api.searchRecipesByServings(name)
        return response.recipes.map { data->
            RecipeResponse(
                id = data.id ?: -1,
                name = data.name ?: "Unknown",
                ingredients = data.ingredients ?: arrayOf("unknown"),
                instructions = data.instructions ?: arrayOf("unknown"),
                prepTimeMinutes = data.prepTimeMinutes ?: -1,
                cookTimeMinutes = data.cookTimeMinutes ?: -1,
                servings = data.servings ?: -1,
                difficulty = data.difficulty ?: "Unknown",
                cuisine = data.cuisine ?: "Unknown",
                image = data.image ?: "Unknown"
            )
        }
    }
    suspend fun searchByDifficulty(name: String): List<RecipeResponse> {
        val response = api.searchRecipesByDifficulty(name)
        return response.recipes.map { data->
            RecipeResponse(
                id = data.id ?: -1,
                name = data.name ?: "Unknown",
                ingredients = data.ingredients ?: arrayOf("unknown"),
                instructions = data.instructions ?: arrayOf("unknown"),
                prepTimeMinutes = data.prepTimeMinutes ?: -1,
                cookTimeMinutes = data.cookTimeMinutes ?: -1,
                servings = data.servings ?: -1,
                difficulty = data.difficulty ?: "Unknown",
                cuisine = data.cuisine ?: "Unknown",
                image = data.image ?: "Unknown"
            )
        }
    }
    suspend fun searchByCuisine(name: String): List<RecipeResponse> {
        val response = api.searchRecipesByCuisine(name)
        return response.recipes.map { data->
            RecipeResponse(
                id = data.id ?: -1,
                name = data.name ?: "Unknown",
                ingredients = data.ingredients ?: arrayOf("unknown"),
                instructions = data.instructions ?: arrayOf("unknown"),
                prepTimeMinutes = data.prepTimeMinutes ?: -1,
                cookTimeMinutes = data.cookTimeMinutes ?: -1,
                servings = data.servings ?: -1,
                difficulty = data.difficulty ?: "Unknown",
                cuisine = data.cuisine ?: "Unknown",
                image = data.image ?: "Unknown"
            )
        }
    }

}
