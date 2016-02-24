package com.cultureoftech.kotlinweather.details

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.utils.bindView
import com.cultureoftech.kotlinweather.weather.CityForecastResponse
import java.text.SimpleDateFormat

/**
 * Created by bgogetap on 2/22/16.
 */
class ForecastItemView(context: Context, screen: DetailsScreen): LinearLayout(context) {

    lateinit var formatter: SimpleDateFormat

    val dateText by bindView<TextView>(R.id.tv_date)
    val minTempText by bindView<TextView>(R.id.tv_temp_min)
    val maxTempTxt by bindView<TextView>(R.id.tv_temp_max)

    init {
        LayoutInflater.from(context).inflate(R.layout.forecast_item, this, true)
        orientation = LinearLayout.VERTICAL
        formatter = screen.component()?.simpleDateFormat()!!
    }

    fun setData(day: CityForecastResponse.Day) {
        dateText.text = formatter.format(day.getDate())
        minTempText.text = context.getString(R.string.temp_min_format, day.temp.min.toInt())
        maxTempTxt.text = context.getString(R.string.temp_max_format, day.temp.max.toInt())
    }
}