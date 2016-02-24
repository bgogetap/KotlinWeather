package com.cultureoftech.kotlinweather.main

import com.cultureoftech.kotlinweather.dagger.ForMain
import dagger.Subcomponent

/**
 * Created by bgogetap on 2/19/16.
 */
@ForMain
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(view: MainView)
}