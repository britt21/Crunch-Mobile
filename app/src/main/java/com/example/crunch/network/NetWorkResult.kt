package com.example.crunch.network

sealed class NetWorkResult<T>(
   val  data : T? = null,
    val message: String? = null
        ) {

    class Sucess<T>(data: T?): NetWorkResult<T>(data)
    class Error<T>(message: String?,data: T? = null): NetWorkResult<T>(data, message)
    class Loading<T>: NetWorkResult<T>()
}