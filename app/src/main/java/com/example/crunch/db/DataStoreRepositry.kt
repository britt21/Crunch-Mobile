package com.example.crunch.db

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.crunch.db.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.crunch.db.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.crunch.db.Constants.Companion.PREFRENCE_DIET_TYPE
import com.example.crunch.db.Constants.Companion.PREFRENCE_DIET_TYPEID
import com.example.crunch.db.Constants.Companion.PREFRENCE_MEAL_TYPE
import com.example.crunch.db.Constants.Companion.PREFRENCE_MEAL_TYPEID
import com.example.crunch.db.Constants.Companion.PREFRENCE_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject



@ActivityRetainedScoped
class DataStoreRepositry @Inject constructor(@ApplicationContext private val context: Context) {

    private object PrefrenceKeys {
        val selectedMealType = stringPreferencesKey(PREFRENCE_MEAL_TYPE)
        val seleectedMealTypeid = intPreferencesKey(PREFRENCE_MEAL_TYPEID)
        val selectedDietType = stringPreferencesKey(PREFRENCE_DIET_TYPE)
        val selectedDietTypeid = intPreferencesKey(PREFRENCE_DIET_TYPEID)
    }

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
        name = PREFRENCE_NAME
    )

    suspend fun saveMealAndDataType(mealType: String, mealTypeid: Int, DietType: String, DietTypeiId: Int){
        context.dataStore.edit { prefrences ->
            prefrences[PrefrenceKeys.selectedMealType] = mealType
            prefrences[PrefrenceKeys.seleectedMealTypeid] = mealTypeid
            prefrences[PrefrenceKeys.selectedDietType] = DietType
            prefrences[PrefrenceKeys.selectedDietTypeid] = DietTypeiId

        }

    }

    val readMealAndDietType : Flow<MealAndDietType> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException){
                emit(emptyPreferences())
            }else{
                throw  exception
            }
        }
        .map { prefrences ->
            val selectedMealType = prefrences[PrefrenceKeys.selectedMealType]?:DEFAULT_MEAL_TYPE
            val selectedMealTypeid = prefrences[PrefrenceKeys.seleectedMealTypeid]?: 0
            val selectedDietType = prefrences[PrefrenceKeys.selectedDietType]?: DEFAULT_DIET_TYPE
            val selectedDietTypeid = prefrences[PrefrenceKeys.selectedDietTypeid]?: 0

            MealAndDietType(
                selectedMealType,
                selectedMealTypeid,
                selectedDietType,
                selectedDietTypeid

            )

        }


}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeid: Int,
    val selectedDietType: String,
    val selectedDietTypeid: Int

)

