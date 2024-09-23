package com.kennysexton.a5dayforecast

import com.kennysexton.a5dayforecast.model.OpenWeatherInterface
import com.kennysexton.a5dayforecast.search.ZipcodeSearchVM
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock


@HiltAndroidTest
class ZipcodeSearchVMTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Mock
    private lateinit var apiService: OpenWeatherInterface

    private lateinit var viewModel: ZipcodeSearchVM

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = ZipcodeSearchVM(apiService)
    }

    @Test
    fun `getCountryCode returns US zip code with country code`() {
        val zipCode = "12345"
        val expectedResult = "$zipCode,us"

        val result = viewModel.getCountryCode(zipCode)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `getCountryCode returns non-US zip code without country code`() {
        val zipCode = "A1B2C3"
        val expectedResult = zipCode

        val result = viewModel.getCountryCode(zipCode)

        assertEquals(expectedResult, result)
    }

    @Test
    fun `isUSZipCode returns true for valid US zip code`() {
        val zipCode = "12345"

        val result = viewModel.isUSZipCode(zipCode)

        assertEquals(true, result)
    }

    @Test
    fun `isUSZipCode returns false for invalid US zip code (less than 5 digits)`() {
        val zipCode = "123"

        val result = viewModel.isUSZipCode(zipCode)

        assertEquals(false, result)
    }

    @Test
    fun `isUSZipCode returns false for invalid US zip code (invalid format)`() {
        val zipCode = "12345a"

        val result = viewModel.isUSZipCode(zipCode)

        assertEquals(false, result)
    }

    @Test
    fun `enableSearchButton returns true for zip code with at least 5 characters`() {
        val zipCode = "12345"

        val result = viewModel.enableSearchButton(zipCode)

        assertEquals(true, result)
    }

    @Test
    fun `enableSearchButton returns false for zip code with less than 5 characters`() {
        val zipCode = "123"

        val result = viewModel.enableSearchButton(zipCode)

        assertEquals(false, result)
    }
}