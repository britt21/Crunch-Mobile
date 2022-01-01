package com.example.crunch

import androidx.lifecycle.LiveData
import com.example.crunch.db.FoodDao
import com.example.crunch.db.FoodEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val foodDao: FoodDao) {

    suspend fun insertFood(foodEntity: FoodEntity) {
        foodDao.Insert(foodEntity)
    }


        fun readDatabase(): Flow<List<FoodEntity>> {
            return foodDao.readFood()
        }
    }
