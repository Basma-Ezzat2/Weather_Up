package com.example.weatherup.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weatherup.Repository.WeatherRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapsViewModel(application: Application) : AndroidViewModel(application) {

    val weatherRepository : WeatherRepo = WeatherRepo(application)

    fun  insertOneWeather(lat: String, lng: String) {
        CoroutineScope(Dispatchers.IO).launch {
            weatherRepository.insertInDB(lat, lng)
        }
    }

}
