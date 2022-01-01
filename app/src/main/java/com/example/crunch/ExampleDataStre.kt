package com.example.crunch

import android.content.Context
import android.preference.PreferenceDataStore
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow

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


