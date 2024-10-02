package com.example.weatherwise.network

import com.example.weatherwise.BuildConfig
import com.example.weatherwise.data.models.WeatherData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("2.5/weather")
    suspend fun fetchWeatherDataByCityName(
        @Query("q") city: String,
        @Query("appid") appId: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "imperial"
    ): Response<WeatherData>

    @GET("2.5/weather")
    suspend fun fetchWeatherDataByCurrentLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "imperial"
    ): Response<WeatherData>
}
