package com.example.crunch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.crunch.FoodTypeConverter


@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)

@TypeConverters(FoodTypeConverter::class)
abstract class FoodDatabase: RoomDatabase(){
    abstract fun foodDao(): FoodDao
}