package com.cultureoftech.kotlinweather.details

import com.cultureoftech.kotlinweather.weather.CityForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bgogetap on 2/21/16.
 */
interface WeatherForecastService {

    @GET("forecast/daily")
    fun getForecast(@Query("id") cityId: Long): Call<CityForecastResponse>
}