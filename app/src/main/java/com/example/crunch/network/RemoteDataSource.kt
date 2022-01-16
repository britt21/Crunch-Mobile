package com.example.crunch.network

import com.example.crunch.foodlist.FoodList
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipieApi: FoodRecipieApi
) {
    suspend fun getRecipies(queries : Map<String, String>) : Response<FoodList>{
        return foodRecipieApi.getFood(queries)
    }
}