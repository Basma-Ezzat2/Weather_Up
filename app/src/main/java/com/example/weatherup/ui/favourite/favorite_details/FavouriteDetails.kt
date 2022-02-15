package com.example.weatherup.ui.favourite.favorite_details

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherup.R
import com.example.weatherup.adapters.HourlyAdapter
import com.example.weatherup.data.model.local.SharedPreference
import com.example.weatherup.data.entity.Daily
import com.example.weatherup.data.entity.Hourly
import com.example.weatherup.databinding.ActivityFavouriteDetailsBinding
import com.example.weatherup.ui.home.getDateString
import com.example.weatherup.ui.home.loadImage
import com.forecast.weather.ui.home.*
import java.util.*

class FavouriteDetails : AppCompatActivity() {
    private lateinit var favouriteDViewModel: FavouriteDViewModel
    private lateinit var binding: ActivityFavouriteDetailsBinding
    lateinit var shared : SharedPreference
    var dailyAdapter = DailyAdapter(arrayListOf())
    var hourlyAdapter = HourlyAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar = supportActionBar
        actionBar?.hide()
        binding = ActivityFavouriteDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        favouriteDViewModel = ViewModelProvider(this).get(FavouriteDViewModel::class.java)
        shared= SharedPreference(this)

        initUI()
        val lat=intent.getStringExtra("Lat")
        val long=intent.getStringExtra("Long")
        getCurrent(lat.toString(), long.toString())

    }
    private fun initUI() {
        binding.recyclerViewDaily.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = dailyAdapter
        }
        binding.recyclerViewHourly.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = hourlyAdapter
        }
    }
    fun getCurrent(lat: String, lan: String){
        favouriteDViewModel.fetchWeatherByID(lat, lan).observe(this, {
            if (it !=null) {
                binding.progressBarLoading.visibility = View.GONE
                var geocoder =
                    Geocoder(this.applicationContext, Locale.getDefault())
                var city = geocoder.getFromLocation(lat.toDouble(), lan.toDouble(), 1)
                Log.i("CITY", city.toString())
                var cityName = if (equals(null)) city[0].featureName else city[0].adminArea
                binding.cityName.text = cityName//last one test
                if (shared.units.equals("metric")) {
                    binding.textViewTemperature.text = it.current.temp.toInt().toString() + getString(R.string.c)
                    binding.textViewWind.text ="Wind Speed "+ it.current.wind_speed.toString() +" "+getString(R.string.ms)
                }
                else if (shared.units.equals("imperial")){
                    binding.textViewTemperature.text = it.current.temp.toInt().toString() + getString(R.string.f)
                    binding.textViewWind.text ="Wind Speed "+it.current.wind_speed.toString() +" "+getString(R.string.mh)
                }
                else {
                    binding.textViewTemperature.text = it.current.temp.toInt().toString() + getString(R.string.k)
                    binding.textViewWind.text ="Wind Speed "+ it.current.wind_speed.toString() +" "+getString(R.string.ms)
                }
                binding.textViewDescription.text = it.current.weather[0].description
                binding.feelsLike.text = getDateString(it.current.dt.toLong(),shared.language.toString())

                binding.textViewClouds.text ="Clouds "+it.current.clouds.toString()+ "%"
                binding.textViewHumidity.text ="Humidity "+it.current.humidity.toString() + "%"
                binding.textViewPressure.text ="Pressure "+ it.current.pressure.toString()+" "+getString(R.string.pa)
                when (it.current.weather[0].icon) {
                    "01n" -> {
                        binding.currentIcon.setImageResource(R.drawable.bg)
                    }
                    "01d" -> {
                        binding.currentIcon.setImageResource(R.drawable.ic_sun)
                    }
                    else -> {
                        loadImage(binding.currentIcon,it.current.weather[0].icon)

                    }
                }
                /*val hour= getHours(it.current.dt.toLong()).split(" ").get(3)
                if (hour.toInt() <7 || hour.toInt()>17)
                {
                    binding.details.setBackgroundResource(R.drawable.night)
                    binding.scroll.setBackgroundResource(R.drawable.night)
                }*/
               /* if (it.current.weather[0].icon.contains("n",ignoreCase = true))
                {
                    binding.details.setBackgroundResource(R.drawable.night)
                    binding.scroll.setBackgroundResource(R.drawable.night)
                }*/
                updateDailyList(it.daily)
                updateHourlyList(it.hourly)
            }
        })
    }
    private fun updateDailyList(it: List<Daily>) {
        dailyAdapter.updateDays(it)
    }
    private fun updateHourlyList(it: List<Hourly>) {
        hourlyAdapter.updateHours(it)
    }
    override fun onPause() {
        finish()
        super.onPause()
    }

}