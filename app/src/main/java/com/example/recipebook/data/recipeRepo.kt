package com.example.recipebook.data

import com.example.recipebook.model.FetchResult
import com.example.recipebook.model.Recipe

interface recipeRepo {
    suspend fun getRecipe(items: String): List<Recipe>
    suspend fun getAllRecipe(): FetchResult
    suspend fun addRecipe(recipe: Map<String, String>)
    suspend fun updateRecipe(recipe: Map<String, String>)
    suspend fun deleteRecipe(id: String)
}