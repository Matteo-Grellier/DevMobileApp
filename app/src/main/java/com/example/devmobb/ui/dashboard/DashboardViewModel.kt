package com.example.devmobb.ui.dashboard

import BusStop
import Station
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _busstop = MutableLiveData<List<BusStop>>().apply {
        value = ArrayList()
    }
    val stations: MutableLiveData<List<BusStop>> = _busstop
}