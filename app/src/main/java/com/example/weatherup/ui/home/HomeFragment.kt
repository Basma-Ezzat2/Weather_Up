package com.example.weatherup.ui.home

import android.location.Address
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherup.R
import com.example.weatherup.adapters.HourlyAdapter
import com.example.weatherup.databinding.FragmentHomeBinding
import com.example.weatherup.data.model.local.SharedPreference
import com.example.weatherup.data.entity.Daily
import com.example.weatherup.data.entity.Hourly
import com.forecast.weather.ui.home.DailyAdapter
import java.util.*

const val LOCATION_REQUEST_CODE = 0
class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var shared: SharedPreference
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var myLat:String
    private lateinit var myLong:String
    private val hourlyAdapter= HourlyAdapter(arrayListOf())
    private val dailyAdapter=DailyAdapter(arrayListOf())
    private lateinit var city: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val shareded= this.context?.let { PreferenceManager.getDefaultSharedPreferences(it) }

       val first = shareded?.getBoolean("firstTime",false)
        if (!first!!){
            findNavController().navigate(R.id.navigation_settings)
        }
        shared= SharedPreference(requireContext())

        if(shared.fromMap && !shared.myLocation){
            myLat =shared.mapLat.toString()
            myLong =shared.mapLong.toString()
        }
        else {
            myLat =shared.lat.toString()
            myLong =shared.long.toString()

        }
        //hourly adapter
        initUI()
        getCurrent(myLat,myLong)
        return binding.root
    }
    private fun initUI() {
        binding.recyclerViewDaily.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dailyAdapter
        }
        binding.recyclerViewHourly.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = hourlyAdapter
        }
    }
    private fun getCurrent(lat: String, lan: String){
        homeViewModel.fetchWeatherByID(lat, lan).observe(viewLifecycleOwner, {
            if (it !=null) {
                binding.progressBarLoading.visibility = View.GONE
                var geocoder =
                    Geocoder(requireContext().applicationContext, Locale.getDefault())
                val addresses: List<Address> = geocoder.getFromLocation(
                    it.lat,
                    it.lon,
                    1
                )
                if (addresses.isNotEmpty()) {
                    city = (addresses[0].getAddressLine(0))
                }
                /*var city = geocoder.getFromLocation(LATITUDE.toDouble(), LONGITUDE.toDouble(), 1)
                Log.i("CITY", city.toString())
                var cityName = if (equals(null)) city[0].featureName else city[0].adminArea*/
                binding.cityName.text = city
                if (shared.units.equals("metric")) {
                    binding.textViewTemperature.text = it.current.temp.toInt().toString() + getString(R.string.c)
                    binding.textViewWind.text ="Wind Speed "+  it.current.wind_speed.toString() +" "+getString(R.string.ms)
                }
                else if (shared.units.equals("imperial")){
                    binding.textViewTemperature.text = it.current.temp.toInt().toString() + getString(R.string.f)
                    binding.textViewWind.text ="Wind Speed "+  it.current.wind_speed.toString() +" "+getString(R.string.mh)
                }
                else {
                    binding.textViewTemperature.text = it.current.temp.toInt().toString() + getString(R.string.k)
                    binding.textViewWind.text ="Wind Speed "+  it.current.wind_speed.toString() +" "+getString(R.string.ms)
                }
                binding.textViewDescription.text = it.current.weather[0].description
                binding.feelsLike.text = getDateString(it.current.dt.toLong(),shared.language.toString())

                binding.textViewClouds.text ="Clouds "+ it.current.clouds.toString()+ "%"
                binding.textViewHumidity.text ="Humidity "+ it.current.humidity.toString() + "%"
                binding.textViewPressure.text ="Pressure "+ it.current.pressure.toString()+" "+getString(R.string.pa)
                when (it.current.weather[0].icon) {
                    "01n" -> {
                        binding.currentIcon.setImageResource(R.drawable.bg)
                    }
                    "01d" -> {
                        binding.currentIcon.setImageResource(R.drawable.ic_sunny)
                    }
                    else -> {
                        loadImage(binding.currentIcon,it.current.weather[0].icon)
                    }
                }
                updateDailyList(it.daily)
                updateHourlyList(it.hourly)
            }
        })
    }
    private fun updateDailyList(it: List<Daily>) {
        dailyAdapter.updateDays(it)
        binding.recyclerViewDaily.visibility=View.VISIBLE
    }
    private fun updateHourlyList(it: List<Hourly>) {
        hourlyAdapter.updateHours(it)
        binding.recyclerViewHourly.visibility=View.VISIBLE
    }

}