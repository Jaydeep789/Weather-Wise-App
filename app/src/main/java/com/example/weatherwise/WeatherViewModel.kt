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
    private val cityDataStore: CityDataStore
): ViewModel() {

    var searchTextState: MutableState<String> = mutableStateOf("")

    val searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)

    private val _weatherData = MutableStateFlow<DataState<WeatherData>>(DataState.Loading)
    val weatherData: StateFlow<DataState<WeatherData>>
        get() = _weatherData

    private lateinit var storedCityText: String

    fun fetchCityWeatherData() {
        try {
            viewModelScope.launch {
                cityDataStore.storeCity(searchTextState.value)
                weatherRepository.getWeatherData(searchTextState.value).collect { data ->
                    _weatherData.value = DataState.Success(data)
                }
            }
        } catch (e: Exception) {
            _weatherData.value = DataState.Error(e)
        }
    }

    fun fetchExistingCityWeatherData(){
         try {
             viewModelScope.launch {
                 cityDataStore.getCity.collect { city ->
                     storedCityText = city
                 }

                 if (storedCityText.isNotBlank()){
                     weatherRepository.getWeatherData(storedCityText).collect { data ->
                         _weatherData.value = DataState.Success(data)
                     }
                 } else {
                     _weatherData.value = DataState.Empty
                 }
             }
         } catch (e: Exception){
             _weatherData.value = DataState.Error(e)
         }
    }

    fun getWeatherDataBasedOnCurrentLocation(lat: Double, long: Double){
        try {
            viewModelScope.launch {
                weatherRepository.getWeatherDataByLocation(lat, long).collect { data ->
                    _weatherData.value = DataState.Success(data)
                    cityDataStore.storeCity(data.name)
                }
            }
        } catch (e: Exception) {
            _weatherData.value = DataState.Error(e)
        }
    }

    fun unableToAccessLocationData(e: Exception){
        _weatherData.value = DataState.Error(e)
    }
}