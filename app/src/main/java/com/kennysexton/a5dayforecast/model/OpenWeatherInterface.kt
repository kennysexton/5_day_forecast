package com.kennysexton.a5dayforecast.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherInterface {

    //api.openweathermap.org/data/2.5/forecast?zip={zip code},{country code}&appid={API key}
    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("zip") zipCode: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "imperial" // default to Fahrenheit
    ): Response<WeatherForecastResponse>
}