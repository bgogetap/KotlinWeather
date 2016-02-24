package com.cultureoftech.kotlinweather.weather

/**
 * Created by bgogetap on 2/20/16.
 */
data class CityWeatherResponse(
        val name: String,
        val id: Long,
        val cod: Long,
//        val weather: Weather, // TODO Gson parsing issue?
        val main: Main,
        val wind: Wind,
        val clouds: Clouds,
        val sys: Sys) {

    data class Main(val temp: Double, val pressure: Double, val humidity: Double, val temp_min: Double, val temp_max: Double)

    data class Weather(val items: Array<WeatherItem>)

    data class WeatherItem(val id: Long, val main: String, val description: String, val icon: String)

    data class Wind(val speed: Double, val deg: Double)

    data class Clouds(val all: Double)

    data class Sys(val message: Double, val country: String, val sunrise: Long, val sunset: Long)
}