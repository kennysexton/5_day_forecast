package com.kennysexton.a5dayforecast.navigation

import com.kennysexton.a5dayforecast.model.WeatherData
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDetails(val city: String, val weatherData: WeatherData)