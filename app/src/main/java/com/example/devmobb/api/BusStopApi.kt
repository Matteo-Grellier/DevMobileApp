package com.example.devmobb.api

import BusStop
import Station
import retrofit2.Response
import retrofit2.http.GET

interface BusStopApi {

    @GET("api/busstop")
    suspend fun getBusStop(): Response<List<BusStop>>
}