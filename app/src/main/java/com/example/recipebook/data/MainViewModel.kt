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

    var searchResult = MutableLiveData<List<Recipe>>()
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
    var addScreenExpanded = mutableStateOf(false)
    init {
        getAllRecipes()
    }

    fun setNewRecipe(){
        newReicpe.value = viewRecipe.value!!
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

    fun getAllRecipes() {
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

    fun updateRecipe() {
        newReicpe.value?.let {
            viewModelScope.launch {
                repo.updateRecipe(it)
            }
        }
    }

    fun viewRecipe(id: String) {
        viewRecipe.value = getRecipe(id)
    }

    private fun getRecipe(id: String): Recipe {
        return recipies.value!!.first{ it.id == id }
    }

    fun searchRecipe(srch: String) {
        searchResult.value = if (srch == ""){
            null
        }else {
            recipies.value?.filter {
                it.name.contains(srch)
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

    fun expandAddScreen() {
        addScreenExpanded.value = true
    }

    fun performOnRecipe() {
        if (newReicpe.value.id != ""){
            updateRecipe()
        }else{
            createRecipe()
        }
    }

    fun clearSearch() {
        searchResult.value = null
    }
}