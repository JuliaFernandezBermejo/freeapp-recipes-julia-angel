package com.example.recipeapp.network

class RecipeResponse (
    val id: Int,
    val name: String,
    val ingredients : Array<String>,
    val instructions : Array<String>,
    val prepTimeMinutes : Int,
    val cookTimeMinutes : Int,
    val servings : Int,
    val difficulty: String,
    val cuisine: String,
    val image: String,

)