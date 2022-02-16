package com.example.weatherup.ui.alert

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherup.data.entity.Alarm
import com.example.weatherup.data.model.local.SharedPreference
import com.example.weatherup.databinding.FragmentAlertBinding
import java.util.*

class AlertsFragment : Fragment() {
    private lateinit var alertsViewModel: AlertsViewModel
    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!
    private lateinit var alertAdapter: AlertAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertsViewModel =
            ViewModelProvider(this).get(AlertsViewModel::class.java)
        _binding = FragmentAlertBinding.inflate(inflater, container, false)
        alertAdapter = AlertAdapter(arrayListOf(),alertsViewModel)
        val root: View = binding.root
        initUI()
        getCurrent()
        buttonAlarmClick()
        return root
    }
    private fun buttonAlarmClick()
    {
        binding.floatingActionButtonAlarm.setOnClickListener {
            activity?.let {
                val intent = Intent(it, AddAlert::class.java)
                it.startActivity(intent)
            }
        }
    }

    private fun initUI() {
        binding.recycleViewAlerts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = alertAdapter
        }
        val itemTouchHelper = ItemTouchHelper(SwipeTodeleteAlarm(alertAdapter,this.requireContext()))
        itemTouchHelper.attachToRecyclerView(binding.recycleViewAlerts)
    }
    fun getCurrent(){
        alertsViewModel.allAlarms().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                binding.back.setBackgroundColor(Color.WHITE)
                updateAlarmList(it)
            }
        })
    }
    private fun updateAlarmList(it: List<Alarm>) {
        alertAdapter.updateAlerts(it)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}