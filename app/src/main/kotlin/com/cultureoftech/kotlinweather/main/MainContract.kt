package com.cultureoftech.kotlinweather.main

import android.location.Location
import android.view.Menu
import com.cultureoftech.kotlinweather.weather.CityWeatherResponse

/**
 * Created by bgogetap on 2/20/16.
 */
interface MainContract {

    interface View {

        fun setData(response: CityWeatherResponse?)

        fun initSearch(menu: Menu?)
    }

    interface Presenter {

        fun loadData(forceRefresh: Boolean)

        fun loadDataForCity(city: String)

        fun loadDataForLocation(location: Location)

        fun loadDetailsForCity()
    }
}