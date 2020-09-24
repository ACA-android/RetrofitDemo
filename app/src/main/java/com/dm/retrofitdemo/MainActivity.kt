package com.dm.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dm.retrofitdemo.api.Weather
import com.dm.retrofitdemo.api.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weatherService = retrofit.create(WeatherService::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val call: Call<Weather> = weatherService.getCurrentWeather("Moscow")
            val response: Response<Weather> = call.execute()
            val weather: Weather = response.body()!!
            Log.d("RESPONSE", weather.toString())
        }
    }
}