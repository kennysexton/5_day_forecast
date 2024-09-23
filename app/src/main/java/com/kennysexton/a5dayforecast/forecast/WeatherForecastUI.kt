package com.kennysexton.a5dayforecast.forecast

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kennysexton.a5dayforecast.model.WeatherData
import com.kennysexton.a5dayforecast.ui.components.ForecastDetails
import com.kennysexton.a5dayforecast.ui.components.ProgressIndicator
import com.kennysexton.a5dayforecast.ui.components.WeatherForecastItem

@Composable
fun ForecastDisplay(vm: WeatherForecastVM, onBackButtonPressed: () -> Unit) {

    val weatherData by vm.weatherResponse.collectAsState()
    val isLoading by vm.showLoading.collectAsState()

    var selectedDay by rememberSaveable { mutableStateOf<WeatherData?>(null) }

    if (isLoading) {
        ProgressIndicator()
    } else {
        Column(modifier = Modifier.padding(16.dp)) {
            Row() {
                IconButton(onClick = {
                    // go back to list view if in details view
                    if (selectedDay != null) selectedDay = null else onBackButtonPressed()
                }) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                }
                Text(
                    text = weatherData?.city?.name ?: "",
                    modifier = Modifier.padding(bottom = 16.dp),
                    style = MaterialTheme.typography.displayMedium
                )
            }

            if (selectedDay != null) {
                ForecastDetails(selectedDay)
            } else {
                LazyColumn {
                    weatherData?.list?.let {
                        items(it.filterIndexed { index, _ ->
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
                            WeatherForecastItem(
                                forecastItem,
                                // When a row is clicked, view the details of that row
                                onRowClicked = { weatherData -> selectedDay = weatherData })
                        }
                    }
                }
            }
        }
    }
}