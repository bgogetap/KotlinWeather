package com.cultureoftech.kotlinweather.base

import com.cultureoftech.kotlinweather.details.DetailsComponent
import com.cultureoftech.kotlinweather.details.DetailsModule
import com.cultureoftech.kotlinweather.details.ForecastItemView
import com.cultureoftech.kotlinweather.main.MainComponent
import com.cultureoftech.kotlinweather.main.MainModule
import com.cultureoftech.kotlinweather.permissions.PermissionRationaleDialog
import dagger.Component
import javax.inject.Singleton

/**
 * Created by bgogetap on 2/16/16.
 */
@Singleton
@Component(modules = arrayOf(MyApplicationModule::class))
interface MyApplicationComponent {

    fun plus(mainModule: MainModule): MainComponent

    fun plus(detailsModule: DetailsModule): DetailsComponent

    fun inject(mainActivity: MainActivity)

    fun inject(dialog: PermissionRationaleDialog)

    fun inject(baseActivity: BaseActivity)

    fun inject(itemView: ForecastItemView)
}