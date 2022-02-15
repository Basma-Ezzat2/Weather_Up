package com.example.weatherup.ui.alert

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherup.databinding.FragmentAlertBinding

class AlertsFragment : Fragment() {
    private lateinit var alertsViewModel: AlertsViewModel
    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        alertsViewModel =
            ViewModelProvider(this).get(AlertsViewModel::class.java)
        _binding = FragmentAlertBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}