package com.example.crunch.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.crunch.foodlist.FoodList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@Dao
interface FoodDao {

    @Query("Select * From FoodEntity")
    fun readFood(): Flow<List<FoodEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(foodEntity: FoodEntity)



}
