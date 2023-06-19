package com.example.recipebook.data

import com.example.recipebook.model.FetchResult
import com.example.recipebook.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject

class recipeRepoImpl @Inject constructor(
    private val api: recipeApi
): recipeRepo {
    override suspend fun getRecipe(items: String): List<Recipe> {
        return withContext(Dispatchers.IO) {
            api.getRecipe(items)
        }
    }

    override suspend fun getAllRecipe(): FetchResult {
       return withContext(Dispatchers.IO) {
           try {
               FetchResult.Success(api.getAllRecipe())
           }catch (e: ConnectException){
               FetchResult.Error(e.message.toString())
           }
        }
    }

    override suspend fun addRecipe(recipe: Recipe) {
        withContext(Dispatchers.IO) {
            api.addNewRecipe(
                name = recipe.name,
                time = recipe.time,
                timeType = recipe.timeType,
                levels = recipe.level,
                servings = recipe.serving,
                image = recipe.image,
                items = recipe.items.toMap(),
                procedure = recipe.procedure
            )
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