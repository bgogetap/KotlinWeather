package com.cultureoftech.kotlinweather.details

import com.cultureoftech.kotlinweather.dagger.ForDetails
import dagger.Subcomponent
import java.text.SimpleDateFormat
import javax.inject.Named

/**
 * Created by bgogetap on 2/21/16.
 */
@ForDetails
@Subcomponent(modules = arrayOf(DetailsModule::class))
interface DetailsComponent {

    fun inject(view: DetailsView)

    fun inject(view: ForecastItemView)

    fun simpleDateFormat(): SimpleDateFormat;

    @Named("cityName") fun cityName(): String?
}