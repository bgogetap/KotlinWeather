package com.cultureoftech.kotlinweather.main

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.utils.bindView
import com.cultureoftech.kotlinweather.weather.CityWeatherResponse

/**
 * Created by bgogetap on 2/20/16.
 */
class WeatherView(context: Context) : LinearLayout(context) {

    val cityName by bindView<TextView>(R.id.tv_city_name)
    val tempText by bindView<TextView>(R.id.tv_temp)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_weather, this, true)
        orientation = LinearLayout.VERTICAL
    }

    fun setData(response: CityWeatherResponse?) {
        cityName.text = response?.name
        tempText.text = response?.main?.temp.toString()
    }
}