package com.cultureoftech.kotlinweather.main

import com.cultureoftech.kotlinweather.dagger.ForMain
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by bgogetap on 2/19/16.
 */
@Module
class MainModule {

    @Provides @ForMain fun provideCityWeatherService(retrofit: Retrofit): CityWeatherService {
        return retrofit.create(CityWeatherService::class.java)
    }
}