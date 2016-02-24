package com.cultureoftech.kotlinweather.animation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

/**
 * Created by bgogetap on 2/19/16.
 */
class DefaultAnimationConfiguration: AnimationConfiguration {

    override fun animateIn(parent: View, viewToAnimate: View, completedRunnable: Runnable) {
        viewToAnimate.x = parent.measuredWidth.toFloat()
        viewToAnimate.animate().x(0.toFloat()).setDuration(300).setListener(object:AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                completedRunnable.run()
            }
        })
    }

    override fun animateOut(parent: View, viewToAnimate: View, completedRunnable: Runnable) {
        viewToAnimate.animate().x(parent.measuredWidth.toFloat()).setDuration(300).setListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                completedRunnable.run()
            }
        })
    }
}