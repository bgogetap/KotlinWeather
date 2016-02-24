package com.cultureoftech.kotlinweather.weather

import java.util.*

/**
 * Created by bgogetap on 2/21/16.
 */
data class CityForecastResponse(val city: City, val list: Array<Day>) {

    data class City(val id: Long, val name: String, val country: String)

    data class Day(val dt: Long, val temp: Temp, val humidity: Double) {

        fun getDate(): Date {
            return Date(dt*1000)
        }
    }

    data class Temp(val day: Double, val min: Double, val max: Double, val night: Double, val eve: Double, val morn: Double)
}