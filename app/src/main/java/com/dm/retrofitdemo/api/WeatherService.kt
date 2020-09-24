package com.dm.retrofitdemo.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "1bd053c7b3d5c4d7869fdc99ce0867c9"

interface WeatherService {

    @GET("data/2.5/weather?appid=$API_KEY&units=metric")
    fun getCurrentWeather(@Query("q")city: String): Call<Weather>
}