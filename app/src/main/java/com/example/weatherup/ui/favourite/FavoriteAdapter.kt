package com.example.weatherup.ui.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherup.R
import com.example.weatherup.data.entity.Weather
import com.example.weatherup.ui.home.loadImage


class FavoriteAdapter(var countries: ArrayList<Weather>, var favoriteCitesViewModel: FavouriteViewModel) :
        RecyclerView.Adapter<FavoriteAdapter.FavoViewHolder>() {

    fun updateCountries(newCountry: List<Weather>) {
        countries.clear()
        countries.addAll(newCountry)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = FavoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.city_row, parent, false)
    )
    override fun getItemCount() = countries.size
    override fun onBindViewHolder(holder: FavoViewHolder, position: Int) {
        holder.bind(countries[position])
        holder.itemView.setOnClickListener {
            Toast.makeText(it.context, " ${countries[position].timezone}", Toast.LENGTH_SHORT).show()
            favoriteCitesViewModel.onClick(countries[position])
        }
    }
    fun deleteItem(pos: Int) {
        favoriteCitesViewModel.deleteCountry(countries[pos])
        countries.removeAt(pos)
        notifyItemRemoved(pos)
    }
    class FavoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageView = view.findViewById<ImageView>(R.id.imageView_icon)
        private val dt = view.findViewById<TextView>(R.id.textView_city)
        private val description = view.findViewById<TextView>(R.id.textView_desc)
        private val temp = view.findViewById<TextView>(R.id.textView_temperature)
        fun bind(country: Weather) {
            dt.text = country.timezone
            description.text = country.current.weather[0].description
            temp.text=country.current.temp.toInt().toString()+"°"
            when (country.current.weather[0].icon) {
                "01n" -> {
                    imageView.setImageResource(R.drawable.bg)
                }
                "01d" -> {
                    imageView.setImageResource(R.drawable.ic_sun)
                }
                else -> {
                    loadImage(imageView,country.current.weather[0].icon)
                }
            }

        }
    }
}