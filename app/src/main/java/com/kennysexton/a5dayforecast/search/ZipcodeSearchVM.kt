package com.kennysexton.a5dayforecast.search

import androidx.lifecycle.ViewModel
import com.kennysexton.a5dayforecast.model.OpenWeatherInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ZipcodeSearchVM @Inject constructor(
    private val apiService: OpenWeatherInterface
) : ViewModel() {


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