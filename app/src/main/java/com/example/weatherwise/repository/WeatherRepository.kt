package com.example.weatherwise.repository

import com.example.weatherwise.data.models.WeatherData
import com.example.weatherwise.network.WeatherApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    fun getWeatherData(city: String): Flow<WeatherData> {
        return weatherApi.fetchWeatherDataByCityName(city = city)
    }

    fun getWeatherDataByLocation(lat: Double, lon: Double): Flow<WeatherData> {
        return weatherApi.fetchWeatherDataByCurrentLocation(lat = lat, lon = lon)
    }
}
