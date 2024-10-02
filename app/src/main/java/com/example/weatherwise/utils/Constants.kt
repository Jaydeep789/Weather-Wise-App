package com.example.weatherwise.utils

import com.example.weatherwise.data.models.Clouds
import com.example.weatherwise.data.models.Coordinates
import com.example.weatherwise.data.models.Main
import com.example.weatherwise.data.models.Rain
import com.example.weatherwise.data.models.Sys
import com.example.weatherwise.data.models.Weather
import com.example.weatherwise.data.models.WeatherData
import com.example.weatherwise.data.models.Wind

object Constants {
    const val WEATHER_DATA_URL = "https://api.openweathermap.org/data/"
    val weatherData = WeatherData(
        base = "stations",
        clouds = Clouds(
            all = 79
        ),
        cod = 200,
        coordinates = Coordinates(
            lon = 21.01,
            lat = 52.22
        ),
        dt = 1727578503,
        main = Main(
            feels_like = 277.69,
            grnd_level = 1010,
            humidity = 83,
            pressure = 1022,
            sea_level = 1022,
            temp = 281.42,
            temp_min = 279.77,
            temp_max = 283.12
        ),
        timezone = 7200,
        sys = Sys(
            country = "PL",
            id = 2032856,
            sunrise = 1727584433,
            sunset = 1727626702,
            type = 2
        ),
        name = "Warsaw",
        id = 756135,
        visibility = 10000,
        wind = Wind(
            deg = 250,
            gust = 0,
            speed = 7.6
        ),
        weather = listOf(
            Weather(
                description = "broken clouds",
                icon = "04n",
                id = 803,
                main = "Clouds"
            )
        ),
        rain = Rain(
            hour = 0.27
        )
    )
}
