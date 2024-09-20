package com.kennysexton.a5dayforecast.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kennysexton.a5dayforecast.R
import com.kennysexton.a5dayforecast.WeatherSearchVM

@Composable
fun ZipCodeSearch(vm: WeatherSearchVM) {


    var zipCode by remember { mutableStateOf("") }
//    var invalidZipCodeError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.search_instructions), modifier = Modifier.padding(16.dp))

        TextField(
            value = zipCode,
            // Only allow numbers and hyphens
            onValueChange = { zipCode = it.replace(Regex("[^0-9-]"), "") })

        // Enable search button if the user has entered in at least 5 characters
        Button(
            onClick = { vm.getWeatherForecast(zipCode) },
            modifier = Modifier.padding(16.dp),
            enabled = vm.enableSearchButton(zipCode)
        ) {
            Text(text = "Search")
        }
    }
}