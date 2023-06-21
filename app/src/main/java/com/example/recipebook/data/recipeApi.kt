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

    @GET("/getRecipes")
    suspend fun getAllRecipe(): List<Recipe>

    @DELETE("/deleteRecipe")
    suspend fun deleteRecipe(
        @Query("id")id: String
    )

    @POST("/insert")
    suspend fun addNewRecipe(
        @Query("name")name: String,
        @Query("time")time: String,
        @Query("timeType")timeType: String,
        @Query("levels")levels: String,
        @Query("servings")servings: String,
        @Query("image")image: String,
        @Query("items")items: Map<String, String>,
        @Query("procedure")procedure: List<String>
    )

    @POST("/updateRecipe")
    suspend fun updateRecipe(
        @Query("id")id: String,
        @Query("name")name: String,
        @Query("time")time: String,
        @Query("timeType")timeType: String,
        @Query("levels")levels: String,
        @Query("servings")servings: String,
        @Query("image")image: String,
        @Query("items")items: Map<String, String>,
        @Query("procedure")procedure: List<String>
    )

}