package com.example.crunch

import android.os.Message

sealed class NetWorkResult<T>(
   val  data : T? = null,
    val message: String? = null
        ) {

    class Sucess<T>(data: T?): NetWorkResult<T>(data)
    class Error<T>(message: String?,data: T? = null): NetWorkResult<T>(data, message)
    class Loading<T>: NetWorkResult<T>()
}