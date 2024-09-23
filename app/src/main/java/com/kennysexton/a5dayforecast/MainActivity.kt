package com.kennysexton.a5dayforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kennysexton.a5dayforecast.forecast.ForecastDisplay
import com.kennysexton.a5dayforecast.navigation.WeatherForecast
import com.kennysexton.a5dayforecast.navigation.ZipcodeSearch
import com.kennysexton.a5dayforecast.search.ZipcodeSearchUI
import com.kennysexton.a5dayforecast.ui.theme.WeatherForecastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = ZipcodeSearch,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<ZipcodeSearch> {
                            ZipcodeSearchUI(navigateToForecast = { countryZipcode ->
                                navController.navigate(WeatherForecast(countryZipcode))
                            })
                        }
                        composable<WeatherForecast> {
                            ForecastDisplay(
                                onBackButtonPressed = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
