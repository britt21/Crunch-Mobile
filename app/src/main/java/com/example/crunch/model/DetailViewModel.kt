package com.example.crunch.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crunch.foodlist.Result
import com.example.crunch.network.PushNotification
import com.example.crunch.network.RetrofitInstance
import com.example.crunch.ui.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailViewModel ( result: Result, application: Application) : AndroidViewModel(application){

    val result = Result
    private val _liveFood  = MutableLiveData<Result>()
    val liveFood : LiveData<Result>
    get() = _liveFood


    var ProductQty = 1


    init {
        _liveFood.value = result
    }


    fun sendNotification(notification: PushNotification) {viewModelScope.launch(Dispatchers.IO) {

        try {
            handleResponse(notification)

        } catch (e: Exception) {
            Log.e(TAG, e.toString())

        }
    }
    }

   private suspend fun handleResponse(notification: PushNotification)  {
       val response = RetrofitInstance.api.postNtification(notification)
       if (response.isSuccessful){
//          val moshi = Moshi.Builder().build()
//           val jsonAdapter = moshi.adapter<PushNotification>(PushNotification::class.java)
//           val pushNotification = jsonAdapter.toJson(notification)
           Log.d(TAG, "Response:${response.body().toString()}")


       }else{
          Log.e(TAG, response.errorBody().toString())
       }



    }


    fun DecTrigger(){
      ProductQty--
    }


    fun incrTriger(){
       ProductQty++
    }







}