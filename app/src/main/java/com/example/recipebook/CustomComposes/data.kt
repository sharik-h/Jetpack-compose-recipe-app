package com.example.recipebook.CustomComposes

import com.example.recipebook.R

fun images(): List<Pair<String, Int>> {
    return  listOf(
        "burger" to R.drawable.burger,
        "cake" to R.drawable.cake,
        "croissant" to R.drawable.croissant,
        "cupcake" to R.drawable.cupcake,
        "ice_cream" to R.drawable.ice_cream,
        "juice" to R.drawable.juice,
        "momo" to R.drawable.momo,
        "noodles" to R.drawable.noodles,
        "pizza" to R.drawable.pizza,
        "pudding" to R.drawable.pudding,
        "salad" to R.drawable.salad,
        "samosa" to R.drawable.samosa,
        "soup" to R.drawable.soup,
        "tea" to R.drawable.tea
    )
}

fun getImgWtihName(name: String): Int? {
    val images = images()
    images.forEach {
        if (it.first == name){
           return it.second
        }
    }
    return null
}

fun colors(): List<Long> {
    return listOf(0xFFE6ACB6, 0xFF9FC6D3, 0xFFD8CF7F, 0xFFDA6262, 0xFF8585D3, 0xFFFFE4E1)
}