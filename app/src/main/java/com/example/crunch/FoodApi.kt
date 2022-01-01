package com.example.crunch

import com.example.crunch.foodlist.FoodList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipieApi {

    @GET("/recipes/complexSearch?")
    suspend fun getFood( @QueryMap queries: Map<String, String>
    ): Response<FoodList>

}



