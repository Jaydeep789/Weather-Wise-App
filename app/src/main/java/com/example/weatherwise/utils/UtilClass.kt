package com.example.weatherwise.utils

sealed class DataState<out T>{
    data class Success<T>(val data: T): DataState<T>()
    data class Error(val error: Throwable): DataState<Nothing>()
    object Loading: DataState<Nothing>()
    object Empty: DataState<Nothing>()
}

enum class Screens(private val title: String){
    WEATHER_SCREEN("weather_screen")
}

enum class SearchAppBarState {
    OPENED,
    CLOSED
}

enum class TrailingIconState {
    READY_TO_DELETE,
    READY_TO_CLOSE
}