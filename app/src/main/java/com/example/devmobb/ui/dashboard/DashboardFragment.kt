package com.example.devmobb.ui.dashboard

import allBusStop
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.devmobb.adapter.BusStopAdapter
import com.example.devmobb.api.BusStopApi
import com.example.devmobb.api.RetrofitHelper
import com.example.devmobb.databinding.FragmentDashboardBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recyclerView = binding.recyclerView
        val progressBarSation = binding.progressBarSation

        /* val stations  = listOf("station 1", "station 2")
        val adapter = StationAdapter(stations, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter */

        dashboardViewModel.stations.observe(viewLifecycleOwner) {
            //val adapter : StationAdapter(it)
            //val adapter : StationAdapter(it.requireContext())
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = BusStopAdapter(it, requireContext())

            allBusStop = it
        }

        val busStopApi = RetrofitHelper().getInstance().create(BusStopApi::class.java)
        GlobalScope.launch {
            val result = busStopApi.getBusStop()
            Log.d("HOME", result.body().toString())
            dashboardViewModel.stations.postValue(result.body())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}