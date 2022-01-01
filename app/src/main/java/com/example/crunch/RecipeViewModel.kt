package com.example.crunch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Index
import com.example.crunch.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.crunch.Constants.Companion.DEFAULT_MEAL_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecipeViewModel @Inject constructor(application: Application, private val dataStoreRepositry: DataStoreRepositry ) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    val readMealAndDietType = dataStoreRepositry.readMealAndDietType

    fun saveMealAndDataType(mealType: String, mealTypeid: Int, DietType: String, DietTypeid: Int) =
        viewModelScope.launch(Dispatchers.IO){
            dataStoreRepositry.saveMealAndDataType(mealType,mealTypeid,DietType,DietTypeid)
        }

   fun applyQueries(): Map<String, String> {
        val queries : HashMap<String, String> = HashMap()

       viewModelScope.launch {
           readMealAndDietType.collect { value ->
               mealType = value.selectedMealType
               dietType = value.selectedDietType
           }
       }

        queries["number"] = "50"
        queries["apiKey"] = Constants.API_KEY
        queries["type"] = mealType
        queries["diet"] = dietType
        queries["addRecipeInformation"] = "true"
        queries["fillIngredients"] = "true"

        return queries

    }

}