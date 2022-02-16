package com.example.weatherup.data.model.local

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPreference(var context: Context) {

    val sp = PreferenceManager.getDefaultSharedPreferences(context)
    val myLocation = sp.getBoolean("USE_DEVICE_LOCATION", true)
    val fromMap = sp.getBoolean("CUSTOM", true)
    val units= sp.getString("UNIT_SYSTEM", "metric")
    val language = sp.getString("LANGUAGE_SYSTEM", "en")
    var lat = sp.getString("lat", "30.630195")
    var long = sp.getString("long", "31.358386")
    var mapLat = sp.getString("mapLat", "30.630195")
    var mapLong = sp.getString("mapLong", "31.358386")
    var alarmType:Boolean = false

}