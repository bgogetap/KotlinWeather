package com.cultureoftech.kotlinweather.main

import com.cultureoftech.kotlinweather.weather.CityWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bgogetap on 2/20/16.
 */
interface CityWeatherService {

    @GET("weather")
    fun getCityWeather(@Query("q") city: String): Call<CityWeatherResponse>

    @GET("weather")
    fun getWeatherFromLocation(@Query("lat") lat: Double, @Query("lon") lon: Double) : Call<CityWeatherResponse>
}