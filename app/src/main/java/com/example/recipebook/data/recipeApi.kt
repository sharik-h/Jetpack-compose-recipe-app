package com.example.recipebook.data

import com.example.recipebook.model.Recipe
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface recipeApi {

    @GET("/getRecipe")
    suspend fun getRecipe(@Query("items") item: String): List<Recipe>

    @GET("/geRecipe")
    suspend fun getAllRecipe(): List<Recipe>

    @DELETE("/deleteRecipe")
    suspend fun deleteRecipe(id: String)

    @POST("/insert")
    suspend fun addNewRecipe(
        @QueryMap options: Map<String, String>,
    )

    @POST("/updateRecipe")
    suspend fun updateRecipe(
        @QueryMap options: Map<String, String>
    )

}