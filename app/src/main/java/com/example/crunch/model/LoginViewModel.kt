package com.example.crunch.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

class LoginViewModel : ViewModel() {



    enum class AuthenticationState{
        AUTHENTICATED, UNAUTHENTICATED
    }


    val authStateListener = FirebaseUserLiveData().map { user ->
        if (user != null){
            AuthenticationState.AUTHENTICATED
        }else{
            AuthenticationState.UNAUTHENTICATED
        }
    }
}