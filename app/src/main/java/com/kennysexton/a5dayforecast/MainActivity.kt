package com.kennysexton.a5dayforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.kennysexton.a5dayforecast.forecast.ForecastDisplay
import com.kennysexton.a5dayforecast.forecast.WeatherForecastVM
import com.kennysexton.a5dayforecast.navigation.NavigationRoutes
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
                        startDestination = NavigationRoutes.ZipCodeSearch.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(route = NavigationRoutes.ZipCodeSearch.route) {
                            ZipcodeSearchUI(navigateToForecast = { countryZipcode ->
                                navController.navigate("${NavigationRoutes.WeatherForecast.route}?countryZipcode=${countryZipcode}")
                            })
                        }
                        composable(
                            route = "${NavigationRoutes.WeatherForecast.route}?countryZipcode={countryZipcode}",
                            arguments = listOf(
                                navArgument("countryZipcode") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            ForecastDisplay(
                                onBackButtonPressed = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
