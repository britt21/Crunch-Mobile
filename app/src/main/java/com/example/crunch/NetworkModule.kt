package com.example.crunch

import com.example.crunch.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providesOkHttp(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory{
        return  GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun ProvideretrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory

    ): Retrofit {
        return Retrofit.Builder()
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()


    }

    @Singleton
    @Provides
    fun FoodApi(retrofit: Retrofit): FoodRecipieApi{
        return retrofit.create(FoodRecipieApi::class.java)

    }
}