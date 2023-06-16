package com.example.recipebook.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: recipeRepoImpl
): ViewModel() {

    var recipies = MutableLiveData<List<Recipe>>()
        private set
    var items = MutableLiveData<String>()
        private set
    var newReicpe = MutableLiveData<Recipe>()
        private set

    init {
        getAllRecipes()
    }

    fun setnewRecipie(name: String, value: String, recipe: Recipe? = null){
        recipe?.let {
            newReicpe.value = recipe
        }
        newReicpe.value?.let {
            when(name) {
                "name" -> it.name = value
                "time" -> it.time = value
                "procedure" -> it.procedure = value
                "items" -> it.items = value
            }
        }
    }

    private fun getAllRecipes() {
        viewModelScope.launch {
            recipies.value = repo.getAllRecipe()
        }
    }

    fun getRecipe() {
        viewModelScope.launch {
            recipies.value = repo.getRecipe(items.value ?: "")
        }
    }

    private fun createRecipe() {
        newReicpe.value?.let {
            val map = mapOf(
                "name" to it.name,
                "time" to it.time,
                "procedure" to it.procedure,
                "items" to it.items
            )
            viewModelScope.launch {
                repo.addRecipe(map)
            }
        }
    }

    fun deleteRecipe(id: String){
        viewModelScope.launch {
            repo.deleteRecipe(id)
            repo.getAllRecipe()
        }
    }

    private fun updateRecipe(id: String) {
        newReicpe.value?.let {
            val map = mapOf(
                "name" to it.name,
                "time" to it.time,
                "procedure" to it.procedure,
                "items" to it.items,
                "id" to id
            )
            viewModelScope.launch {
                repo.updateRecipe(map)
            }
        }
    }

}