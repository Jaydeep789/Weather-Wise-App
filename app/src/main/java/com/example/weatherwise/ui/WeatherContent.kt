package com.example.weatherwise.ui

import androidx.compose.runtime.Composable
import com.example.weatherwise.data.models.WeatherData
import com.example.weatherwise.utils.DataState

@Composable
fun WeatherContent(
    weatherData: DataState<WeatherData>
) {
    if (weatherData is DataState.Success){

    } else if (weatherData is DataState.Error){

    } else if (weatherData is DataState.Loading){

    } else {

    }
}