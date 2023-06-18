package com.example.recipebook.model

sealed class FetchResult {
    data class Success(val data: List<Recipe>): FetchResult()
    data class Error(val message: String): FetchResult()
}
