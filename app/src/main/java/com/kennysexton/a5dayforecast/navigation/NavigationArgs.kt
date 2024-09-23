package com.kennysexton.a5dayforecast.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

data class NavigationArgs(val searchZipcode: String) {
    val weatherForecastArgs = listOf(navArgument("searchZipcode") {
        type = NavType.StringType
    })
}
