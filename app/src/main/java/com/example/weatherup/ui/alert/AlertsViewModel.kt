package com.example.weatherup.ui.alert

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherup.Repository.WeatherRepo
import com.example.weatherup.data.entity.Alarm

class AlertsViewModel (val app: Application) : AndroidViewModel(app) {
    private val weatherRepository: WeatherRepo = WeatherRepo(app)
    private val navigate: MutableLiveData<Alarm> = MutableLiveData<Alarm>()
    val notificationID :MutableLiveData<Int> = MutableLiveData<Int>()

    fun allAlarms(): LiveData<List<Alarm>> {
        return weatherRepository.getAllAlerts()
    }
    fun updateAlarm(id: Int, switch:Boolean){
        weatherRepository.updateAlarm(id,switch)
    }
    fun updateIdAlarm(id: Int, newId: Int){
        weatherRepository.updateIdAlarm(id,newId)
    }

    fun deleteAlarm(alarm: Alarm) {
        weatherRepository.deleteAlarm(alarm)
    }
    //navigation
    fun onClick(alarm: Alarm) {
        navigate.value = alarm
    }

    fun cancelAlertContext(context: Context, id: Int){
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager?
        val myIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, id, myIntent,  PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager!!.cancel(pendingIntent)
        Log.i("hh","cancel Done")
    }
}