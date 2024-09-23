package com.kennysexton.a5dayforecast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kennysexton.a5dayforecast.model.WeatherData

@Composable
fun ForecastDetails(selectedDay: WeatherData?) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(selectedDay?.dt_txt ?: "")
        Text(selectedDay?.weather?.first()?.description ?: "")
        Text("Expected Temperature: ${selectedDay?.main?.temp}")
        Text("Humidity: ${selectedDay?.main?.humidity}")
        Text("Feels Like: ${selectedDay?.main?.feels_like}")
        Text("Min Temp: ${selectedDay?.main?.temp_min}")
        Text("Min Temp: ${selectedDay?.main?.temp_max}")
    }
}