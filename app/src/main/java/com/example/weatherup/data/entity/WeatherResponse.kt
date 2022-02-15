package com.example.weatherup.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["lon", "lat"])

data class WeatherResponse(
    @PrimaryKey(autoGenerate = true)
    var uid : Any = 0.0,
    @field:SerializedName("alerts")
    val alerts: List<Alert?>? = null,
    @SerializedName("current")
    @Expose
    val current: Current,
    @SerializedName("daily")
    @Expose
    val daily: List<Daily>,
    @SerializedName("hourly")
    @Expose
    val hourly: List<Hourly>,
    @SerializedName("lat")
    @Expose
    val lat: Double, // 30.4898
    @SerializedName("lon")
    @Expose
    val lon: Double, // 99.7713
    @SerializedName("timezone")
    @Expose
    val timezone: String, // Asia/Shanghai
    @SerializedName("timezone_offset")
    @Expose
    val timezoneOffset: Any // 28800
)


