package com.example.weatherwise

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.weatherwise.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.weatherwise.data.models.WeatherData
import com.example.weatherwise.datastore.CityDataStore
import com.example.weatherwise.utils.DataState
import com.example.weatherwise.utils.SearchAppBarState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val cityDataStore: CityDataStore,
) : ViewModel() {

    var searchTextState: MutableState<String> = mutableStateOf("")

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    private val _weatherData = MutableStateFlow<DataState<WeatherData>>(DataState.Loading)
    val weatherData: StateFlow<DataState<WeatherData>>
        get() = _weatherData

    private lateinit var storedCityText: String

    fun fetchCityWeatherData() {
        viewModelScope.launch {
            cityDataStore.storeCity(searchTextState.value)
            _weatherData.value = weatherRepository.getWeatherData(searchTextState.value)
        }
    }

    fun fetchExistingCityWeatherData() {
        viewModelScope.launch {
            cityDataStore.getCity.collect { city ->
                storedCityText = city
            }

            if (storedCityText.isNotBlank()) {
                _weatherData.value = weatherRepository.getWeatherData(storedCityText)
            } else {
                _weatherData.value = DataState.Empty
            }
        }
    }

    fun getWeatherDataBasedOnCurrentLocation(lat: Double, long: Double) {
        viewModelScope.launch {
            _weatherData.value = weatherRepository.getWeatherDataByLocation(lat, long)
            if (_weatherData.value is DataState.Success){
                cityDataStore.storeCity((_weatherData.value as DataState.Success<WeatherData>).data.name)
            }
        }
    }

    fun unableToAccessLocationData(e: Exception) {
        _weatherData.value = DataState.Error(e)
    }
}
