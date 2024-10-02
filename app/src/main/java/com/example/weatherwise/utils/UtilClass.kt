package com.example.weatherwise.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import java.util.Locale

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
fun calculateTimeFromTimeStamp(): String {
    val instant = Instant.now()
    val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
    val sb = StringBuilder()
        .append(localDate.dayOfMonth.toString())
        .append(" ")
        .append(localDate.month.toString())
        .append(", ")
        .append(localDate.dayOfWeek.toString())
    return sb.toString()
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateTime(timeInSeconds: Long): String {
    val date = Date(timeInSeconds)
    val format = SimpleDateFormat("HH:mm", Locale.getDefault())
    return format.format(date)
}
