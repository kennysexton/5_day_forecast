package com.kennysexton.a5dayforecast.model


data class WeatherResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherData>,
)

data class WeatherData(
    val dt: Long,
    val main: MainWeatherMetrics,
//    val weather: WeatherIcon,
//    val wind: Wind,
//    val clouds: Clouds,
    val visibility: Int,
    val pop: Double,
//    val sys: Sys,
    val dt_txt: String
)

data class MainWeatherMetrics(
    val temp: Long,
    val feels_like: Long,
    val temp_min: Long,
    val temp_max: Long,
    val pressure: Int,
    val sea_level: Int,
    val grnd_level: Int,
    val humidity: Int,
    val temp_kf: Double
)
