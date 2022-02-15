package com.forecast.weather.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherup.R
import com.example.weatherup.data.model.local.SharedPreference
import com.example.weatherup.data.entity.Daily
import com.example.weatherup.ui.home.getDateStrings
import com.example.weatherup.ui.home.loadImage

import kotlin.collections.ArrayList


class DailyAdapter(var days: ArrayList<Daily>) :
    RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {

    fun updateDays(newDays: List<Daily>) {
        days.clear()
        days.addAll(newDays)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = DailyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.daily_row, parent, false)
    )

    override fun getItemCount() = days.size

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        holder.bind(days[position],position)
    }

    class DailyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.daily_icon)
        private val dt = view.findViewById<TextView>(R.id.daily_date)
        private val description = view.findViewById<TextView>(R.id.daily_desc)
        private val temp = view.findViewById<TextView>(R.id.daily_temp)

        fun bind(day: Daily,position: Int) {
            val shared= SharedPreference(view.context)
            when (position) {
                0 -> {
                    dt.text = view.context.getString(R.string.today)//Today
                }
                1 -> {
                    dt.text = view.context.getString(R.string.tomorrow)
                }
                else -> {
                    dt.text = getDateStrings(day.dt.toLong(),shared.language.toString()).split(",").get(0)
                }
            }
            if(shared.language=="en"){
                temp.text=day.temp.max.toInt().toString()+"° / "+day.temp.min.toInt().toString()+"°"
            }else {
                temp.text=day.temp.min.toInt().toString()+"° / "+day.temp.max.toInt().toString()+"°"
            }
            description.text = day.weather[0].description
            when (day.weather[0].icon) {
                "01n" -> {
                    imageView.setImageResource(R.drawable.bg)
                }
                "01d" -> {
                    imageView.setImageResource(R.drawable.ic_sunny)
                }
                else -> {
                    loadImage(imageView,day.weather[0].icon)

                }
            }
        }
    }

}