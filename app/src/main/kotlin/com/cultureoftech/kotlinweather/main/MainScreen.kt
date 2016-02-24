package com.cultureoftech.kotlinweather.main

import android.content.Context
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.animation.AnimationConfiguration
import com.cultureoftech.kotlinweather.animation.DefaultAnimationConfiguration
import com.cultureoftech.kotlinweather.base.MyApplication
import com.cultureoftech.kotlinweather.ui.Screen

/**
 * Created by bgogetap on 2/15/16.
 */
class MainScreen(): Screen<MainView> {

    private var component: MainComponent? = null

    override fun view(context: Context): MainView {
        return MainView(context, this)
    }

    override fun component(): MainComponent? {
        if (component == null) {
            component = MyApplication.component.plus(MainModule())
        }
        return component
    }

    override fun menuResource(): Int = R.menu.menu_main

    override fun destroyComponent() {
        component = null
    }

    override fun animationConfiguration(): AnimationConfiguration = DefaultAnimationConfiguration()
}