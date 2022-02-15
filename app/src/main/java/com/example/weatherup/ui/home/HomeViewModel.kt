package com.example.weatherup.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.weatherup.data.entity.Weather
import com.example.weatherup.Repository.WeatherRepo

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val weatherRepository : WeatherRepo = WeatherRepo(application)

    fun fetchWeatherByID(lat: String, lng: String): LiveData<Weather> {
        return weatherRepository.getAndInsertWeather(lat, lng)
    }
}