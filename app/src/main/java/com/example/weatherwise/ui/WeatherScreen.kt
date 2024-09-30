package com.example.weatherwise.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.weatherwise.WeatherViewModel

@Composable
fun WeatherScreen(
    weatherViewModel: WeatherViewModel
) {

    val searchAppBarState by weatherViewModel.searchAppBarState
    val searchTextState by weatherViewModel.searchTextState

    val weatherData by weatherViewModel.weatherData.collectAsState()

    Scaffold(
        topBar = {
            DisplayAppBar(
                weatherViewModel = weatherViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                WeatherContent(
                    weatherData = weatherData
                )
            }
        }
    )
}
