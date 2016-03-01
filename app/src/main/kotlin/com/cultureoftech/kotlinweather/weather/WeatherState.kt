package com.cultureoftech.kotlinweather.weather

import com.cultureoftech.kotlinweather.R


enum class WeatherState(val drawableRes: Int, vararg apiCodes: String) {

    CLEAR_DAY(R.drawable.ic_clear_day, "01d"),
    CLEAR_NIGHT(R.drawable.ic_clear_night, "01n"),
    FEW_CLOUDS_DAY(R.drawable.ic_few_clouds_day, "02d"),
    FEW_CLOUDS_NIGHT(R.drawable.ic_few_clouds_night, "02n"),
    CLOUDS(R.drawable.ic_clouds, "03d", "03n", "04d", "04n"),
    RAIN(R.drawable.ic_rain, "09d", "09n", "10d", "10n"),
    THUNDERSTORM(R.drawable.ic_thunderstorm, "11d", "11n"),
    SNOW(R.drawable.ic_snow, "13d", "13n"),
    MIST(R.drawable.ic_mist, "50d", "50n"),
    INVALID(R.drawable.ic_mist, "");

    val apiCodes = apiCodes;

    companion object {
        fun fromApiCode(apiCode: String?): WeatherState {
            for (state in values()) {
                state.apiCodes.forEach { if (it.equals(apiCode)) return state }
            }
            return INVALID
        }
    }
}