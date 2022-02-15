package com.example.weatherup.data.model.remote

import com.example.weatherup.data.entity.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
var EXCLUDE = "minutely"
const val API_KEY = "7f37e6369c5ab6a7d578670815e3bdfc"
var LANGUAGE = "en"
var LATITUDEFAV = "31.2001"
var LONGITUDE = "31.3785"
var LATITUDE = "31.0409"
var LONGITUDEFAV = "29.9187"


interface NetworkApi {
    @GET("data/2.5/onecall?")
    fun getCurrentData(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("lang") lang: String,
        @Query("appId") appId: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String
    ): Call<Weather>
}