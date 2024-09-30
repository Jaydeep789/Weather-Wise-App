package com.example.weatherwise.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityDataStore(private val context: Context) {

    val getCity: Flow<String> = context.myDataStore.data.map { preferences ->
        preferences[CITY_KEY] ?: ""
    }

    suspend fun storeCity(city: String){
        context.myDataStore.edit { preferences ->
            preferences[CITY_KEY] = city
        }
    }

    companion object {
        private val Context.myDataStore by preferencesDataStore("CityName")
        val CITY_KEY = stringPreferencesKey("city_key")
    }
}