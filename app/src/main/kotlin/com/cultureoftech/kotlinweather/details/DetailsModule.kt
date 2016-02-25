package com.cultureoftech.kotlinweather.details

import com.cultureoftech.kotlinweather.dagger.ForDetails
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.text.SimpleDateFormat
import javax.inject.Named

/**
 * Created by bgogetap on 2/21/16.
 */
@Module
class DetailsModule(val cityId: Long, val cityName: String?) {

    @Provides @Named("cityId") fun provideCityId(): Long {
        return cityId
    }

    @Provides @Named("cityName") fun provideCityName(): String? {
        return cityName;
    }

    @Provides @ForDetails fun provideDateFormatter(): SimpleDateFormat {
        return SimpleDateFormat("EEE, MMM d")
    }

    @Provides @ForDetails
    fun provideWeatherForecastService(retrofit: Retrofit): WeatherForecastService {
        return retrofit.create(WeatherForecastService::class.java)
    }
}