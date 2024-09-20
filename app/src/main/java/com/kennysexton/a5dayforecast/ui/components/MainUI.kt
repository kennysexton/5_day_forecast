package com.kennysexton.a5dayforecast.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.kennysexton.a5dayforecast.WeatherSearchVM

@Composable
fun MainUI(innerPadding: PaddingValues) {
    val vm = hiltViewModel<WeatherSearchVM>()
    val weatherList by vm.weatherResponseList.collectAsState()

    if (weatherList == null) {
        ZipCodeSearch(vm)
    } else {
        ForecastDisplay(weatherList!!, onBackButtonPressed = {})
    }


}