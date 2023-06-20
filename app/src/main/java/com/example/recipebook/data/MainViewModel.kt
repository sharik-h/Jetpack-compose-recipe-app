package com.example.recipebook.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipebook.model.FetchResult
import com.example.recipebook.model.Recipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: recipeRepoImpl
): ViewModel() {

    var recipies: MutableLiveData<List<Recipe>> = MutableLiveData()
        private set
    var newReicpe = mutableStateOf(Recipe())
        private set
    var viewRecipe = MutableLiveData<Recipe>()
        private set
    var exceptoins = mutableStateOf("")
    var itemName = mutableStateOf("")
    var itemQty = mutableStateOf("")
    var step = mutableStateOf("")
    init {
        getAllRecipes()
    }

    fun setnewRecipie(name: String, value: String, recipe: Recipe? = null){
        recipe?.let {
            newReicpe.value = recipe
        }
        newReicpe.let {
            when(name) {
                "name" -> it.value = it.value.copy(name = value)
                "time" -> it.value = it.value.copy(time = value)
                "servings" -> it.value = it.value.copy(serving = value)
                "level" -> it.value = it.value.copy(level = value)
                "timeType" -> it.value = it.value.copy(timeType = value)
                "image" -> it.value = it.value.copy(image = value)
                "itemName" -> itemName.value = value
                "itemQty" -> itemQty.value = value
                "step" -> step.value = value
            }
        }
    }

    private fun getAllRecipes() {
        viewModelScope.launch {
            val result = repo.getAllRecipe()
            when(result){
                is FetchResult.Success -> {
                    recipies.value = result.data
                }
                is FetchResult.Error -> {
                    exceptoins.value = result.message
                }
            }
        }
    }

    fun getRecipe() {
        viewModelScope.launch {
            recipies.value = repo.getRecipe(items.value ?: "")
        }
    }

    fun createRecipe() {
        viewModelScope.launch {
            repo.addRecipe(newReicpe.value)
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

    fun addStep() {
        if (step.value != "") {
            newReicpe.value?.procedure?.add(step.value)
            step.value = ""
        }
    }
    fun addItems() {
        if (itemName.value != "" && itemQty.value != "") {
            newReicpe.value?.items?.add(itemName.value to itemQty.value)
            itemQty.value = ""
            itemName.value = ""
        }
    }

    fun validateInput(): Boolean {
        newReicpe.value.let {
            return it.procedure.isNotEmpty() && it.items.isNotEmpty()
                    && it.name.isNotEmpty() && it.time.isNotEmpty()
        }
    }

    fun nothingInputed(): Boolean {
        newReicpe.value.let {
            return it.procedure.isEmpty() && it.items.isEmpty()
                    && it.name.isEmpty() && it.time.isEmpty()
        }
    }

    fun deleteItem(item: Pair<String, String>) {
        newReicpe.value.items.remove(item)
    }

    fun clearData() {
        newReicpe.let {
            it.value = it.value.copy(name = "")
            it.value = it.value.copy(time = "")
            it.value = it.value.copy(serving = "")
            it.value = it.value.copy(level = "")
            it.value = it.value.copy(timeType = "")
        }
    }
}