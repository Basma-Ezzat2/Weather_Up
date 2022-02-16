package com.example.weatherup.ui.alert

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.weatherup.Repository.WeatherRepo
import com.example.weatherup.data.entity.Alarm


class AddAlarmViewModel(app : Application) : AndroidViewModel(app) {

    private val weatherRepo : WeatherRepo = WeatherRepo(app)

    fun  insertOneAlarm(alarm: Alarm) {
        weatherRepo.insertAlert(alarm)
    }
    fun returnId():LiveData<Int>{
        return weatherRepo.returnId()
    }
    fun updateIdAlarm(id: Int, newId: Int){
        weatherRepo.updateIdAlarm(id,newId)
    }

}