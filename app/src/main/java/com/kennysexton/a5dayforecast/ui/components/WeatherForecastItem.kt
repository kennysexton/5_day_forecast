package com.kennysexton.a5dayforecast.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kennysexton.a5dayforecast.model.WeatherData
import kotlin.math.roundToInt


@Composable
fun WeatherForecastItem(forecastItem: WeatherData, onRowClicked: (WeatherData) -> Unit) {
    Column(modifier = Modifier
        .padding(vertical = 16.dp)
        .clickable { onRowClicked(forecastItem) }) {
        // UTC by default. If this was a production app we would convert to the local time of the area
        Text(forecastItem.dt_txt)
        Text("${forecastItem.main.temp.roundToInt()}°F")
        Text(forecastItem.weather.first().description)
    }
}