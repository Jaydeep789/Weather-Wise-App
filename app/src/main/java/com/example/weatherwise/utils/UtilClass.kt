package com.example.weatherwise.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.format.DateTimeFormatter

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

@RequiresApi(Build.VERSION_CODES.O)
fun calculateTimeFromTimeStamp(timeInSeconds: Long, pattern: String): String {
    val stamp = Instant.ofEpochSecond(timeInSeconds)
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return formatter.format(stamp)
}