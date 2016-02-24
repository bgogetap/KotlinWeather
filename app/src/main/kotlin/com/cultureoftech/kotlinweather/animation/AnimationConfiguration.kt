package com.cultureoftech.kotlinweather.animation

import android.view.View

/**
 * Created by bgogetap on 2/18/16.
 */
interface AnimationConfiguration {

    fun animateIn(parent: View, viewToAnimate: View, completedRunnable: Runnable)

    fun animateOut(parent: View, viewToAnimate: View, completedRunnable: Runnable)
}