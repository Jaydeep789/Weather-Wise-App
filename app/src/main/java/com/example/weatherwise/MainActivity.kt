package com.example.weatherwise

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherwise.ui.WeatherScreen
import com.example.weatherwise.ui.theme.WeatherWiseTheme
import com.example.weatherwise.utils.Screens
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var locationPermissionResultLauncher: ActivityResultLauncher<String>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            WeatherWiseTheme {
                val viewModel: WeatherViewModel by viewModels()
                locationPermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestPermission(),
                    onResult = { isGranted ->
                        if (isGranted) {
                            try {
                                if (ContextCompat.checkSelfPermission(this@MainActivity,
                                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                                ) {
                                    val fusedLocationProvider = LocationServices.getFusedLocationProviderClient(baseContext.applicationContext)
                                    fusedLocationProvider.lastLocation
                                        .addOnSuccessListener { location ->
                                            location?.let { it ->
                                                viewModel.getWeatherDataBasedOnCurrentLocation(
                                                    it.latitude,
                                                    it.longitude
                                                )
                                            } ?: viewModel.fetchExistingCityWeatherData()
                                        }
                                        .addOnFailureListener { exception ->
                                            viewModel.unableToAccessLocationData(exception)
                                        }
                                }
                            } catch (e: Exception) {
                                viewModel.unableToAccessLocationData(e)
                            }
                        } else {
                            viewModel.fetchExistingCityWeatherData()
                        }
                    }
                )
                val navHostController = rememberNavController()
                WeatherWiseApp(
                    navHostController = navHostController,
                    weatherViewModel = viewModel
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun WeatherWiseApp(
        navHostController: NavHostController,
        weatherViewModel: WeatherViewModel
    ) {

        NavHost(
            navController = navHostController,
            startDestination = Screens.WEATHER_SCREEN.name,
            modifier = Modifier
        ) {
            composable(
                route = Screens.WEATHER_SCREEN.name
            ) {
                LaunchedEffect(key1 = Unit) {
                    locationPermissionResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                }

                WeatherScreen(
                    weatherViewModel = weatherViewModel
                )
            }
        }
    }
}
