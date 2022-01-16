package com.example.crunch.db

import androidx.room.TypeConverter
import com.example.crunch.foodlist.FoodList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class FoodTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodList: FoodList): String{
        return gson.toJson(foodList)
    }

    @TypeConverter
    fun stringToJson(data: String): FoodList{
        val listType = object : TypeToken<FoodList>(){}.type
        return gson.fromJson(data, listType)
    }
}