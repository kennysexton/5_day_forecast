package com.kennysexton.a5dayforecast

import androidx.lifecycle.ViewModel
import com.kennysexton.a5dayforecast.model.OpenWeatherInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherLandingVM @Inject constructor(
    private val apiService: OpenWeatherInterface
) : ViewModel() {
    fun getWeatherForecast(zipCode: String) {
        TODO("Not yet implemented")
    }


}