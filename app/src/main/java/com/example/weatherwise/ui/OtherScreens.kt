package com.example.weatherwise.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherwise.ui.theme.WeatherWiseTheme
import com.example.weatherwise.ui.theme.chakraPetchFontFamily
import com.example.weatherwise.ui.theme.subTitleContentColor

@Composable
fun ErrorScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = Icons.Filled.Warning,
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colorScheme.subTitleContentColor
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "We are facing some issues. Please try again later.",
            fontFamily = chakraPetchFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            maxLines = 2,
            color = MaterialTheme.colorScheme.subTitleContentColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun InitialScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(56.dp),
            tint = MaterialTheme.colorScheme.subTitleContentColor
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = "Please try searching a city or share your location",
            fontFamily = chakraPetchFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            maxLines = 2,
            color = MaterialTheme.colorScheme.subTitleContentColor,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(56.dp)
        )
    }
}

@Preview
@Composable
private fun LoadingScreenPreview() {
    WeatherWiseTheme {
        LoadingScreen()
    }
}

@Preview
@Composable
private fun InitialScreenPreview() {
    WeatherWiseTheme {
        InitialScreen()
    }
}

@Preview
@Composable
private fun ErrorScreenPreview() {
    WeatherWiseTheme {
        ErrorScreen()
    }
}
