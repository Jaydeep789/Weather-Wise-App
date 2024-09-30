package com.example.weatherwise.network

import com.example.weatherwise.BuildConfig
import com.example.weatherwise.data.models.WeatherData
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun fetchWeatherDataByCityName(
        @Query("q") city: String,
        @Query("appid") appId: String = BuildConfig.WEATHER_API_KEY,
    ): Flow<WeatherData>

    @GET("weather")
    fun fetchWeatherDataByCurrentLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = BuildConfig.WEATHER_API_KEY,
    ): Flow<WeatherData>
}