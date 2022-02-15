package com.example.weatherup.data.model.remote

import com.example.weatherup.data.entity.Weather
import retrofit2.Call


class RetrofitBuilder (private var networkInterface : NetworkApi = ApiServices.createRetrofit() ) {

    fun getCurrentLocationWeather(
        latitude: String,
        longitude: String,
        languageCode: String,
        units: String
    ): Call<Weather> {
        return networkInterface
            .getCurrentData(
                lat = latitude,
                lon = longitude,
                exclude = "minutely",
                lang = languageCode,
                units = units,
                appId = "7f37e6369c5ab6a7d578670815e3bdfc"
            )
    }


}
