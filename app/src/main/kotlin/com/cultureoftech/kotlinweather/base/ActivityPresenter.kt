package com.cultureoftech.kotlinweather.base

import com.cultureoftech.kotlinweather.ui.PresenterRoot
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bgogetap on 2/21/16.
 */
@Singleton
class ActivityPresenter @Inject constructor(): PresenterRoot<MainActivity>() {

    fun registerResultDelegate(delegate: ActivityResultDelegate) {
        getView()?.registerResultDelegate(delegate)
    }

    fun unregisterResultDelegate(delegate: ActivityResultDelegate) {
        getView()?.unregisterResultDelegate(delegate)
    }

    fun resetToolbarMenu() {
        getView()?.resetToolbarMenu()
    }

    override fun viewAttached() {
    }

    override fun viewDetached() {
    }
}