package com.example.weatherup.data.model.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.weatherup.data.entity.Weather

class LocalDB(application: Application) {

    private val weatherDao = WeatherDB.getInstance(application).weatherDao()


    fun getAllWeather(): LiveData<List<Weather>> {
        return weatherDao.getAllWeather()
    }

    fun insertWeather(weatherList: Weather){
        weatherDao.insertAll(weatherList)
    }

    fun getWeatherByLatLon(lat: String, lon: String): LiveData<Weather> {
        return weatherDao.getOneWeatherByLatLon(lat.substring(0,4)+"%", lon.substring(0,4)+"%")
    }

    fun  getOneWeatherToAlert(lat: String, lon: String): Weather {
        return weatherDao. getOneWeatherToAlert(lat.substring(0,4)+"%", lon.substring(0,4)+"%")
    }
    fun delete(weather: Weather) {
        weatherDao.delete(weather)
    }


}