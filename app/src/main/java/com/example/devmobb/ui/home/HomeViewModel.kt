package com.example.devmobb.ui.home

import Station
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _stations = MutableLiveData<List<Station>>().apply {
        value = ArrayList()
    }
    val stations: MutableLiveData<List<Station>> = _stations
}