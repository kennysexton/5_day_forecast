package com.kennysexton.a5dayforecast.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
fun ZipcodeSearchUI(
    navigateToForecast: (String) -> Unit
) {

    val vm = hiltViewModel<ZipcodeSearchVM>()

    var zipcode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(stringResource(id = R.string.search_instructions), modifier = Modifier.padding(16.dp))

        TextField(
            value = zipcode,
            // Only allow numbers and hyphens
            onValueChange = { zipcode = it.replace(Regex("[^0-9-]"), "") })

        // Enable search button if the user has entered in at least 5 characters
        Button(
            onClick = { navigateToForecast(vm.getCountryCode(zipcode)) },
            modifier = Modifier.padding(16.dp),
            enabled = vm.enableSearchButton(zipcode)
        ) {
            Text(text = "Search")
        }
    }
}