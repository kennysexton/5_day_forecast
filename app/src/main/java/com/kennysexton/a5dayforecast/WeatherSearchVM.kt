package com.kennysexton.a5dayforecast

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kennysexton.a5dayforecast.model.OpenWeatherInterface
import com.kennysexton.a5dayforecast.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherSearchVM @Inject constructor(
    private val apiService: OpenWeatherInterface
) : ViewModel() {

    private val _weatherResponseList = MutableStateFlow<WeatherResponse?>(null)
    val weatherResponseList: StateFlow<WeatherResponse?> = _weatherResponseList.asStateFlow()

//    private val _invalidZipCode = MutableStateFlow(false)
//    val invalidZipCode: StateFlow<Boolean> = _invalidZipCode.asStateFlow()

    fun getWeatherForecast(zipCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    apiService.getWeatherForecast(
                        getCountryCode(zipCode),
                        BuildConfig.OPEN_WEATHER_KEY
                    )

                if (response.isSuccessful) {
                    _weatherResponseList.value = response.body()
                    println(response.body())
                } else {
                    Log.e("Networking", "failed call to OpenWeatherAPI")
                }
            } catch (e: Exception) {
                Log.e("Networking", "exception when calling over network: ${e.message}")
            }
        }
    }

    /**
     * We have to include a country code in the network call,
     * for simplicity, only supporting the US
     */
    fun getCountryCode(zipCode: String): String {
        return if (isUSZipCode(zipCode)) {
            "$zipCode,us"
        } else {
            // Add other country validation logic here
            zipCode
        }
    }

    /**
     * Valid US Zipcodes are at least 5 digits long
     * US Zip Code Example:
     * 18235 or 18235-2239
     */
    fun isUSZipCode(zipCode: String): Boolean {
        // \d{5}: Matches exactly 5 digits.
        // (-\d{4})?: Matches a hyphen followed by 4 digits, and this entire group is optional.
        val zipCodeRegex = Regex("^\\d{5}(-\\d{4})?$")

        return zipCodeRegex.matches(zipCode)
    }

    fun enableSearchButton(zipCode: String): Boolean {
        return zipCode.length >= 5
    }


}