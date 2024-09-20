package com.kennysexton.a5dayforecast.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kennysexton.a5dayforecast.WeatherSearchVM

@Composable
fun MainUI(innerPadding: PaddingValues) {
    val vm = hiltViewModel<WeatherSearchVM>()
    val weatherList by vm.weatherResponseList.collectAsState()

    Column(modifier = Modifier.padding(innerPadding)) {
        if (weatherList == null) {
            ZipCodeSearch(vm)
        } else {
            ForecastDisplay(weatherList!!, onBackButtonPressed = {})
        }
    }


}