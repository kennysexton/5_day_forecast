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
import com.kennysexton.a5dayforecast.WeatherLandingVM

@Composable
fun ZipCodeSearch(innerPadding: PaddingValues) {

    val vm = hiltViewModel<WeatherLandingVM>()
    var zipCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.search_instructions), modifier = Modifier.padding(16.dp))

        TextField(value = zipCode, onValueChange = { zipCode = it })

        // TODO: only enable search button if the user has entered in the right # of characters
        Button(onClick = { vm.getWeatherForecast(zipCode) }, modifier = Modifier.padding(16.dp)) {
            Text(text = "Search")
        }
    }

}