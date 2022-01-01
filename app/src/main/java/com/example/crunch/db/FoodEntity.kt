package com.example.crunch.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.crunch.foodlist.FoodList


@Entity
data class FoodEntity(
    @PrimaryKey
    val foodList: FoodList
)

