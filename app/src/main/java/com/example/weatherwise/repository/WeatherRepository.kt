package com.example.weatherwise.repository

import com.example.weatherwise.data.models.WeatherData
import com.example.weatherwise.datastore.CityDataStore
import com.example.weatherwise.network.WeatherApi
import com.example.weatherwise.utils.DataState
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val cityDataStore: CityDataStore
) {

    var cityText: String = ""

    suspend fun getWeatherData(city: String): DataState<WeatherData> {
        return try {
            val dataResponse = weatherApi.fetchWeatherDataByCityName(city = city)

            val result = dataResponse.body()!!
            if (dataResponse.isSuccessful && result.cod >= 200 && result.cod <= 300){
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
            if (responseData.isSuccessful && result.cod >= 200 && result.cod <= 300){
                DataState.Success(result)
            } else {
                DataState.Error(Exception())
            }
        } catch (e: Exception){
            DataState.Error(e)
        }
    }

    suspend fun getStoredCity(){
        cityDataStore.getCity.collect { city ->
            cityText = city
        }
    }
}
