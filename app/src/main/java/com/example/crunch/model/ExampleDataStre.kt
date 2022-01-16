package com.example.crunch.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

data class UserPreferences(val showCompleted: Boolean){

    val Context.dataStore by preferencesDataStore(
        name = USER_PREfRENCES_NAME
    )




}

private const val USER_PREfRENCES_NAME = "user_prefrences"


class UserPreferencesRepository(
    private val userPrefrenceStore : DataStore<UserPreferences>,
    context: Context){

    private object PreferencesKeys {
        val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
    }



}


