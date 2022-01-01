package com.example.crunch.foodlist


import com.google.gson.annotations.SerializedName

data class FoodList(
    @SerializedName("results")
    val results: List<Result>
)