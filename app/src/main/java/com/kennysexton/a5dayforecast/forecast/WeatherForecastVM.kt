package com.kennysexton.a5dayforecast.forecast

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennysexton.a5dayforecast.BuildConfig
import com.kennysexton.a5dayforecast.model.OpenWeatherInterface
import com.kennysexton.a5dayforecast.model.WeatherForecastResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastVM @Inject constructor(
    private val apiService: OpenWeatherInterface,
) : ViewModel() {

    private val _weatherResponse = MutableStateFlow<WeatherForecastResponse?>(null)
    val weatherResponse: StateFlow<WeatherForecastResponse?> =
        _weatherResponse.asStateFlow()

    private val _showLoading = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading.asStateFlow()

    fun getWeatherForecast(countryZipcode: String) {
        // Show Spinner
        _showLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    apiService.getWeatherForecast(
                        countryZipcode,
                        BuildConfig.OPEN_WEATHER_KEY
                    )

                if (response.isSuccessful) {
                    _weatherResponse.value = response.body()
                    println(response.body())
                    _showLoading.value = false
                } else {
                    Log.e("Networking", "failed call to OpenWeatherAPI")
                    _showLoading.value = false
                }
            } catch (e: Exception) {
                Log.e("Networking", "exception when calling over network: ${e.message}")
                _showLoading.value = false
            }
        }
    }
}