package com.cultureoftech.kotlinweather.main

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.utils.bindView
import com.cultureoftech.kotlinweather.weather.CityWeatherResponse
import com.cultureoftech.kotlinweather.weather.WeatherState

/**
 * Created by bgogetap on 2/20/16.
 */
class WeatherView(context: Context) : LinearLayout(context) {

    val cityName by bindView<TextView>(R.id.tv_city_name)
    val tempText by bindView<TextView>(R.id.tv_temp)
    val description by bindView<TextView>(R.id.tv_weather_description)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_weather, this, true)
        orientation = LinearLayout.HORIZONTAL
        val sidePadding: Int = resources.getDimension(R.dimen.side_padding).toInt()
        setPadding(sidePadding, 0, sidePadding, 0)
        setGravity(Gravity.CENTER_VERTICAL)
    }

    fun setData(response: CityWeatherResponse?) {
        cityName.text = response?.name
        tempText.text = response?.main?.temp.toString()
        val state = WeatherState.fromApiCode(response?.weather?.get(0)?.icon)
        description.text = response?.weather?.get(0)?.description
        description.setCompoundDrawablesWithIntrinsicBounds(0, state.drawableRes, 0, 0)
    }
}