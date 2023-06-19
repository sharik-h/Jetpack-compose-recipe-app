package com.example.recipebook.model

data class Recipe(
    var id: String = "",
    var name: String = "",
    var time: String = "",
    var level: String = "beginner",
    var serving: String = "1",
    var procedure: MutableList<String> = mutableListOf(),
    var timeType: String = "minutes",
    var items: MutableList<Pair<String, String>> = mutableListOf(),
    var image: String = ""
)
