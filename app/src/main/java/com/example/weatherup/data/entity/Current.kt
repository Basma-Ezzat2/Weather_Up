package com.example.weatherup.data.entity

import com.forecast.weather.dataLayer.entity.WeatherCurrent

data class Current(
    val clouds: Int,
    val dew_point: Double,
    val dt: Int,
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Double,
    val uvi: Double,
    val visibility: Int,
    val weather: List<WeatherCurrent>,
    val wind_deg: Int,
    val wind_speed: Double
)