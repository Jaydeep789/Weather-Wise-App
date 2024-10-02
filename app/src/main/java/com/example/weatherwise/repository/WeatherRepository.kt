package com.example.weatherwise.repository

import com.example.weatherwise.data.models.WeatherData
import com.example.weatherwise.network.WeatherApi
import com.example.weatherwise.utils.DataState
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi
) {

    suspend fun getWeatherData(city: String): DataState<WeatherData> {
        return try {
            val dataResponse = weatherApi.fetchWeatherDataByCityName(city = city)

            val result = dataResponse.body()!!
            if (dataResponse.isSuccessful){
                DataState.Success(result)
            } else {
                DataState.Error(Exception())
            }
        }
        catch (e: Exception){
            DataState.Error(e)
        }
    }

    suspend fun getWeatherDataByLocation(lat: Double, lon: Double): DataState<WeatherData> {
        return try {
            val responseData = weatherApi.fetchWeatherDataByCurrentLocation(lat = lat, lon = lon)

            val result = responseData.body()!!
            if (responseData.isSuccessful){
                DataState.Success(result)
            } else {
                DataState.Error(Exception())
            }
        } catch (e: Exception){
            DataState.Error(e)
        }
    }
}
