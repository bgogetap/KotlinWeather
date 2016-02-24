package com.cultureoftech.kotlinweather.details

import com.cultureoftech.kotlinweather.weather.CityForecastResponse

/**
 * Created by bgogetap on 2/21/16.
 */
interface DetailsContract {

    interface View {

        fun setData(response: CityForecastResponse?)
    }

    interface Presenter {

        fun loadData()
    }
}