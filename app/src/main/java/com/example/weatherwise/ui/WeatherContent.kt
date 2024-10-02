package com.example.weatherwise.ui

import android.os.Build
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherwise.R
import com.example.weatherwise.data.models.WeatherData
import com.example.weatherwise.ui.theme.WeatherWiseTheme
import com.example.weatherwise.ui.theme.cardScreenBackgroundColor
import com.example.weatherwise.ui.theme.chakraPetchFontFamily
import com.example.weatherwise.ui.theme.russoOneRegular
import com.example.weatherwise.ui.theme.screenBackgroundColor
import com.example.weatherwise.ui.theme.screenContentColor
import com.example.weatherwise.ui.theme.subTitleContentColor
import com.example.weatherwise.utils.Constants
import com.example.weatherwise.utils.DataState
import com.example.weatherwise.utils.calculateTime
import com.example.weatherwise.utils.calculateTimeFromTimeStamp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherContent(
    weatherData: DataState<WeatherData>
) {
    when (weatherData) {
        is DataState.Success -> {
            DataScreen(weatherData = weatherData.data)
        }

        is DataState.Error -> {
            ErrorScreen()
        }

        is DataState.Loading -> {
            LoadingScreen()
        }

        else -> {
            InitialScreen()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DataScreen(
    weatherData: WeatherData
) {
    val date = calculateTimeFromTimeStamp()
    val sunrise = calculateTime(weatherData.sys.sunrise.toLong())
    val sunset = calculateTime(weatherData.sys.sunset.toLong())

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.screenBackgroundColor)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            Text(
                text = weatherData.name,
                fontFamily = russoOneRegular,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.screenContentColor,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text(
                text = date,
                fontFamily = chakraPetchFontFamily,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.subTitleContentColor,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .size(150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${weatherData.main.temp.toInt()}°",
                        fontFamily = russoOneRegular,
                        fontSize = 56.sp,
                        color = MaterialTheme.colorScheme.screenContentColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = weatherData.weather.first().main,
                        fontFamily = chakraPetchFontFamily,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.subTitleContentColor,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Column(
                    modifier = Modifier.size(150.dp)
                ) {
                    AsyncImage(
                        model = "https://openweathermap.org/img/wn/${weatherData.weather.first().icon}@2x.png",
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            WeatherCardInfo(
                firstIcon = R.drawable.wind_48,
                firstItemData = "${weatherData.wind.speed} m/s",
                firstItemText = "Wind",
                secondIcon = R.drawable.humidity_48,
                secondItemData = "${weatherData.main.humidity}%",
                secondItemText = "Humidity",
                thirdIcon = R.drawable.rain_48,
                thirdItemData = "100%",
                thirdItemText = "Rain"
            )
            Spacer(Modifier.height(24.dp))
            WeatherCardInfo(
                firstIcon = R.drawable.sea_waves,
                firstItemData = "${weatherData.main.seaLevel} m",
                firstItemText = "Sea Level",
                secondIcon = R.drawable.ground,
                secondItemData = "${weatherData.main.groundLevel} m",
                secondItemText = "Grnd level",
                thirdIcon = R.drawable.atmospheric_pressure_48,
                thirdItemData = "${weatherData.main.pressure} lb",
                thirdItemText = "Pressure"
            )
            Spacer(Modifier.height(24.dp))
            WeatherCardInfo(
                firstIcon = R.drawable.feels_like,
                firstItemData = "${weatherData.main.feelsLike.toInt()}°F",
                firstItemText = "Feels Like",
                secondIcon = R.drawable.sunrise_48,
                secondItemData = sunrise,
                secondItemText = "Sunrise",
                thirdIcon = R.drawable.sunset_48,
                thirdItemData = sunset,
                thirdItemText = "Sunset"
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
fun WeatherCardInfo(
    @DrawableRes firstIcon: Int,
    firstItemData: String,
    firstItemText: String,
    @DrawableRes secondIcon: Int,
    secondItemData: String,
    secondItemText: String,
    @DrawableRes thirdIcon: Int,
    thirdItemData: String,
    thirdItemText: String,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.cardScreenBackgroundColor,
            contentColor = MaterialTheme.colorScheme.screenContentColor,
            disabledContentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CardInfo(
                icon = firstIcon,
                itemData = firstItemData,
                itemText = firstItemText
            )
            CardInfo(
                icon = secondIcon,
                itemData = secondItemData,
                itemText = secondItemText
            )
            CardInfo(
                icon = thirdIcon,
                itemData = thirdItemData,
                itemText = thirdItemText
            )
        }
    }
}

@Composable
fun CardInfo(
    @DrawableRes icon: Int,
    itemData: String,
    itemText: String,
) {
    Column(
        modifier = Modifier
            .size(100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(50.dp).padding(bottom = 4.dp),
            tint = Color.White
        )
        Text(
            text = itemData,
            fontFamily = chakraPetchFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.screenContentColor
        )
        Text(
            text = itemText,
            fontFamily = chakraPetchFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.subTitleContentColor,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun CardInfoPreview() {
    WeatherWiseTheme {
        CardInfo(
            icon = R.drawable.wind_48,
            itemData = "10 m/s",
            itemText = "Wind"
        )
    }
}

@Preview
@Composable
private fun WeatherCardInfoPreview() {
    WeatherWiseTheme {
        WeatherCardInfo(
            firstIcon = R.drawable.wind_48,
            firstItemData = "10 m/s",
            firstItemText = "Wind",
            secondIcon = R.drawable.humidity_48,
            secondItemData = "$98%",
            secondItemText = "Humidity",
            thirdIcon = R.drawable.rain_48,
            thirdItemData = "$100%",
            thirdItemText = "Rain"
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun DataScreenPreview() {
    WeatherWiseTheme {
        DataScreen(
            weatherData = Constants.weatherData
        )
    }
}
