package com.example.recipebook.data

import com.example.recipebook.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class recipeRepoImpl @Inject constructor(
    private val api: recipeApi
): recipeRepo {
    override suspend fun getRecipe(items: String): List<Recipe> {
        return withContext(Dispatchers.IO) {
            api.getRecipe(items)
        }
    }

    override suspend fun getAllRecipe(): List<Recipe> {
       return withContext(Dispatchers.IO) {
            api.getAllRecipe()
        }
    }

    override suspend fun addRecipe(recipe: Map<String, String>) {
        withContext(Dispatchers.IO) {
            api.addNewRecipe(recipe)
        }
    }

    override suspend fun updateRecipe(recipe: Map<String, String>) {
        withContext(Dispatchers.IO) {
            api.updateRecipe(recipe)
        }
    }

    override suspend fun deleteRecipe(id: String) {
        withContext(Dispatchers.IO){
            api.deleteRecipe(id)
        }
    }

}