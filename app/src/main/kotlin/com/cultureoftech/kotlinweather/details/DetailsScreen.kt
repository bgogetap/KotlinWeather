package com.cultureoftech.kotlinweather.details

import android.content.Context
import com.cultureoftech.kotlinweather.animation.AnimationConfiguration
import com.cultureoftech.kotlinweather.animation.DefaultAnimationConfiguration
import com.cultureoftech.kotlinweather.base.MyApplication
import com.cultureoftech.kotlinweather.ui.Screen

/**
 * Created by bgogetap on 2/21/16.
 */
class DetailsScreen(val cityId: Long, val cityName: String?): Screen<DetailsView> {

    private var component: DetailsComponent? = null

    override fun view(context: Context): DetailsView {
        return DetailsView(context, this)
    }

    override fun component(): DetailsComponent? {
        if (component == null) {
            component = MyApplication.component.plus(DetailsModule(cityId, cityName))
        }
        return component
    }

    override fun menuResource(): Int = -1

    override fun destroyComponent() {
        component = null
    }

    override fun animationConfiguration(): AnimationConfiguration = DefaultAnimationConfiguration()
}