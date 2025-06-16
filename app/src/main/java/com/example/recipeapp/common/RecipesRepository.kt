package com.example.recipeapp.common

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.example.recipeapp.network.RecipesAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RecipesRepository {

    /*private val api: RecipesAPI

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    api = Retrofit.Builder()
    .baseUrl("https://fortnite-api.com/v2/")
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()
    .create(FortniteAPI::class.java)*/

    fun getFavourites(){

    }
}