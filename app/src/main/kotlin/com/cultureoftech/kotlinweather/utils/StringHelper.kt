package com.cultureoftech.kotlinweather.utils

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bgogetap on 2/22/16.
 */
@Singleton
class StringHelper @Inject constructor(val context: Context) {

    fun getString(resId: Int): String {
        return context.getString(resId)
    }

    fun getString(resId: Int, varargs: Any): String {
        return context.getString(resId, varargs)
    }
}