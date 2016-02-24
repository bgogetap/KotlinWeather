package com.cultureoftech.kotlinweather.permissions

import android.Manifest
import com.cultureoftech.kotlinweather.R

/**
 * Created by bgogetap on 2/21/16.
 */
enum class Permission(val id: Int, val permissionString: String, val rationaleTitle: Int, val rationaleMessage: Int) {

    LOCATION(1, Manifest.permission.ACCESS_FINE_LOCATION, R.string.location_rationale_title, R.string.location_rationale_message),
    INVALID(-1, "", -1, -1);

    companion object {
        fun fromId(id: Int): Permission {
            values().forEach { if (id == it.id) return it }
            return INVALID
        }
    }
}