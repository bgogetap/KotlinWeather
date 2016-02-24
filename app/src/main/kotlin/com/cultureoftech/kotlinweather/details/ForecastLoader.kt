package com.cultureoftech.kotlinweather.details

import com.cultureoftech.kotlinweather.weather.CityForecastResponse
import retrofit2.Retrofit
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by bgogetap on 2/21/16.
 */
class ForecastLoader @Inject constructor(val retrofit: Retrofit) {

    val service = retrofit.create(WeatherForecastService::class.java)

    fun getForecastForCity(cityId: Long): Observable<CityForecastResponse> {
        return Observable.fromCallable({ service.getForecast(cityId).execute().body() })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}