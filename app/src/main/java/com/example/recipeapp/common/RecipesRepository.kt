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
            .baseUrl("https://github.com/Ovi/DummyJSON/blob/master/database/recipes.json")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RecipesAPI::class.java)
    }

    suspend fun getFavourites() =
        try {
            withContext(Dispatchers.IO) {
                val favResponse = api.getFavourites()
                val favourites = ArrayList<News>()
                    for (dataResponse in favResponse.favlist) {
                    news.add(News(motd.title, motd.tabTitle, motd.tileImage, motd.body))
                }
                Result.success(Pair(newsResponse.data.image, news))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
}