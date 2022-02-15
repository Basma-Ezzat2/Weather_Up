package com.example.weatherup.ui.favourite.favorite_details

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherup.Repository.WeatherRepo
import com.example.weatherup.data.entity.Weather


class FavouriteDViewModel(app : Application) : AndroidViewModel(app) {
    val weatherRepository : WeatherRepo = WeatherRepo(app)

    fun fetchWeatherByID(lat: String, lng: String): LiveData<Weather> {
        return weatherRepository.getAndInsertWeather(lat, lng)
    }
    fun fetchWeather(lat: String, lng: String): LiveData<List<Weather>> {
        return weatherRepository.getWeathers(lat, lng)
    }

}