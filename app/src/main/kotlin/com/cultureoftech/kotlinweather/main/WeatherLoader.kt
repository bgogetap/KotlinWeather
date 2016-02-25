package com.cultureoftech.kotlinweather.main

import com.cultureoftech.kotlinweather.weather.CityWeatherResponse
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by bgogetap on 2/20/16.
 */
class WeatherLoader @Inject constructor(val service: CityWeatherService) {

    fun getWeatherForCity(city: String): Observable<CityWeatherResponse> {
        return Observable.fromCallable({ service.getCityWeather(city).execute().body() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getWeatherForLocation(lat: Double, lon: Double): Observable<CityWeatherResponse> {
        return Observable.fromCallable({ service.getWeatherFromLocation(lat, lon).execute().body() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}