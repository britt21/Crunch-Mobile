package com.example.crunch

import android.app.Application
import androidx.lifecycle.*
import com.example.crunch.db.FoodEntity
import com.example.crunch.foodlist.FoodList
import com.example.crunch.foodlist.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class FoodViewModel  @Inject constructor( val repository: Repository, application: Application) : AndroidViewModel(application) {

    enum class FoodStatus{ Loading, Error, Done}

    private val _navigatTodelected = MutableLiveData<Result>()
    val navigateToselected : LiveData<Result>
    get() = _navigatTodelected

    fun displayProperty(result: Result){
        _navigatTodelected.value = result
    }

    fun displayComplete(){
        _navigatTodelected.value = null
    }
 val livefood : MutableLiveData<NetWorkResult<FoodList>> = MutableLiveData()
val readFood: LiveData<List<FoodEntity>> = repository.local.readDatabase().asLiveData()

    private fun insertFood(foodEntity: FoodEntity) =
        viewModelScope.launch (Dispatchers.IO){
            repository.local.insertFood(foodEntity)
        }

    private val _status = MutableLiveData<FoodStatus>()

    // The external immutable LiveData for the request status
    val status: LiveData<FoodStatus>
        get() = _status


    fun getFood(queries : Map<String, String> ) {
        viewModelScope.launch {
               getRecipieSafeCall(queries)

        }

    }

    private suspend fun getRecipieSafeCall(queries: Map<String, String>) {
       livefood.value = NetWorkResult.Loading()
        try {


            val response = repository.remote.getRecipies(queries)
            livefood.value = handleFoodResponse(response)

            val foodList = livefood.value!!.data
            if (foodList != null){
                offlineCacheFood(foodList)
            }

        } catch (e: Exception) {

   livefood.value = NetWorkResult.Error("No internet Connection")

        }


    }

    private fun offlineCacheFood(foodList: FoodList) {
        val foodEntity = FoodEntity(foodList)
         insertFood(foodEntity)

    }

    private fun handleFoodResponse(response: Response<FoodList>): NetWorkResult<FoodList>? {
        when{
            response.message().toString().contains("timeout") ->{
                return NetWorkResult.Error("TimeOut")
            }

            response.code() == 402 ->{
                return NetWorkResult.Error("Api Key Limited")
            }
            response.body()?.results.isNullOrEmpty() -> {
                return NetWorkResult.Error("Recipies Not Found")

            }

            response.isSuccessful ->{
                val foodRecipes = response.body()
                return NetWorkResult.Sucess(foodRecipes!!)
            }
            else ->{
                return NetWorkResult.Error(response.message())
            }
        }

    }

    private val _navigateToselectedFood =  MutableLiveData<Result>()
    val navigateToSelectedProperty : LiveData<Result>
    get() = _navigateToselectedFood

    fun displayPropertyDetails(result: Result){
        _navigateToselectedFood.value = result
    }

    fun displayPropretydetailsComplete(){

        _navigateToselectedFood.value = null
    }

}