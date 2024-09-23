package com.kennysexton.a5dayforecast.navigation

sealed class NavigationRoutes(val route: String) {
    data object ZipCodeSearch : NavigationRoutes("zipcode_search")
    data object WeatherForecast : NavigationRoutes("weather_forecast")
}