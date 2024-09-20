package com.kennysexton.a5dayforecast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kennysexton.a5dayforecast.model.WeatherData
import com.kennysexton.a5dayforecast.model.WeatherResponse
import kotlin.math.roundToInt

@Composable
fun ForecastDisplay(weatherData: WeatherResponse, onBackButtonPressed: () -> Unit) {

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = weatherData.city.name,
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.displayMedium
        )
        LazyColumn {
            items(weatherData.list.filterIndexed { index, _ ->
                // The openweather forecast call returns every 3 hours,
                // To get the next days time, we show every 8th item
                // [0] Day 0 18:00
                // [1] Day 0 21:00
                // [2] Day 1 00:00
                // ...
                // [8] Day 1 18:00
                index % 8 == 0
            }
            ) { forecastItem ->
                WeatherForecastItem(forecastItem)
            }
        }
    }
}

@Composable
fun WeatherForecastItem(forecastItem: WeatherData) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        // UTC by default. If this was a production app we would convert to the local time of the area
        Text(forecastItem.dt_txt)
        Text("${forecastItem.main.temp.roundToInt()}°F")
        Text(forecastItem.weather.first().description)

    }
}