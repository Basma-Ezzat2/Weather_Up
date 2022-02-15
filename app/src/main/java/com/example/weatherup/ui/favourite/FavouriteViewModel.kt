package com.example.weatherup.ui.favourite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherup.Repository.WeatherRepo
import com.example.weatherup.data.entity.Weather

class FavouriteViewModel (val app: Application) : AndroidViewModel(app){
    val weatherRepository : WeatherRepo = WeatherRepo(app)
    private val navigate: MutableLiveData<Weather> = MutableLiveData<Weather>()

    fun allCountries() :LiveData<List<Weather>> {
        return weatherRepository.returnAll()
    }
    fun deleteCountry(weather: Weather){
        weatherRepository.deleteItem(weather)
    }

    //navigation
    fun onClick(weather: Weather) {
        navigate.value = weather
    }

    fun getnavigation(): LiveData<Weather> {
        return navigate
    }
}