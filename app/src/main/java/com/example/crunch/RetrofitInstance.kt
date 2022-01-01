package com.example.crunch

import com.example.crunch.Constants.Companion.BASE_NOTIFY_URL
import com.example.crunch.Constants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class RetrofitInstance {

    companion object{

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private  val  retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_NOTIFY_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        val api by lazy {
            retrofit.create(NotificatinApi::class.java)
        }
    }
}