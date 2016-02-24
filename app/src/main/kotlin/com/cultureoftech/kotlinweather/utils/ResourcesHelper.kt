package com.cultureoftech.kotlinweather.utils

import android.content.Context

@Suppress("DEPRECATION")
/**
 * Created by bgogetap on 2/22/16.
 */
object ResourcesHelper {

    fun getColor(context: Context, colorRes: Int): Int {
        if (VersionHelper.isBelowMarshmallow()) {
            return context.resources.getColor(colorRes)
        } else {
            return context.getColor(colorRes)
        }
    }
}