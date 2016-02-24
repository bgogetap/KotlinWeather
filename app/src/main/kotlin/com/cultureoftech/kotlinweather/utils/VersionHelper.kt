package com.cultureoftech.kotlinweather.utils

import android.os.Build

/**
 * Created by bgogetap on 2/21/16.
 */
object VersionHelper {

    fun isBelowMarshmallow(): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M
    }
}