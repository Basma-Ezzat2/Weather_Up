package com.example.weatherup.ui.favourite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherup.data.entity.Weather
import com.example.weatherup.databinding.FragmentFavouriteBinding
import com.example.weatherup.ui.favourite.favorite_details.FavouriteDetails
import com.example.weatherup.ui.map.Maps

class FavouriteFragment : Fragment() {

    private lateinit var favouriteViewModel: FavouriteViewModel
    private var _binding: FragmentFavouriteBinding? = null
    lateinit var countryAdapter : FavoriteAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteViewModel =
            ViewModelProvider(this).get(FavouriteViewModel::class.java)
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        countryAdapter = FavoriteAdapter(arrayListOf(),favouriteViewModel)
        binding.floatingActionButton.setOnClickListener {
            activity?.let {
                val intent = Intent(it, Maps::class.java)
                it.startActivity(intent)
            }
        }
        initUI()
        getCurrent()

        favouriteViewModel.getnavigation().observe(viewLifecycleOwner, {
            if (it!=null) {
                val intent = Intent(this.context, FavouriteDetails::class.java)
                intent.putExtra("Lat", it.lat.toString())
                intent.putExtra("Long", it.lon.toString())
                Log.i("hh","intent ${it.lat} /// ${it.lon}")
                startActivity(intent)
            }
        })
        val root: View = binding.root
        return root
    }
    private fun initUI() {
        binding.recyclerFav.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryAdapter
        }
        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(countryAdapter,this.requireContext()))
        itemTouchHelper.attachToRecyclerView(binding.recyclerFav)

    }
    fun getCurrent(){
        favouriteViewModel.allCountries().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                updateCountryList(it)
            }
        })
    }
    private fun updateCountryList(it: List<Weather>) {
        countryAdapter.updateCountries(it)
    }

}