package com.cultureoftech.kotlinweather.utils

import android.content.Context
import android.graphics.drawable.Drawable

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

    fun getDrawable(context: Context, drawableRes: Int): Drawable {
        if (VersionHelper.isBelowLollipop()) {
            return context.resources.getDrawable(drawableRes)
        } else {
            return context.getDrawable(drawableRes)
        }
    }
}