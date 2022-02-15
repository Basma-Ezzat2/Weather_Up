package com.example.weatherup.data.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServices {
    private const val BASE_URL = "https://api.openweathermap.org/"
    fun createRetrofit(): NetworkApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(NetworkApi::class.java)
    }
}