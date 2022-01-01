package com.example.crunch

import com.example.crunch.Constants.Companion.CONTENT_TYE
import com.example.crunch.Constants.Companion.SERVER_KEY
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotificatinApi {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:$CONTENT_TYE")
    @POST("fcm/send")
    suspend fun postNtification(
        @Body notification: PushNotification
    ): Response<ResponseBody>

}