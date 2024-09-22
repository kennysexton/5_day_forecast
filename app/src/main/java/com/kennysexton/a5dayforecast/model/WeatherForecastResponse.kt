package com.kennysexton.a5dayforecast.model

import kotlinx.serialization.Serializable

@Serializable
data class WeatherForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherData>,
    val city: City
)

@Serializable
data class WeatherData(
    val dt: Long,
    val main: MainWeatherMetrics,
    val weather: List<WeatherIcon>,
//    val wind: Wind,
//    val clouds: Clouds,
    val visibility: Int,
    val pop: Double,
//    val sys: Sys,
    val dt_txt: String
)

@Serializable
data class MainWeatherMetrics(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)

@Serializable
data class WeatherIcon(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

@Serializable
data class City(
    val name: String,
    val country: String,
)
