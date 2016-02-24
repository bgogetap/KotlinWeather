package com.cultureoftech.kotlinweather.ui

import android.content.Context
import android.view.View
import com.cultureoftech.kotlinweather.animation.AnimationConfiguration

/**
 * Created by bgogetap on 2/18/16.
 */
interface Screen<T: View> {

    fun view(context: Context): T

    fun component(): Any?

    fun menuResource(): Int

    fun destroyComponent()

    fun animationConfiguration(): AnimationConfiguration
}