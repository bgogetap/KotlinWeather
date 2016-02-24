package com.cultureoftech.kotlinweather.ui

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bgogetap on 2/18/16.
 */
@Singleton
class UiManager @Inject constructor(val backStack: BackStack): PresenterRoot<UiManager.View>() {

    fun push(screen: Screen<*>) = getView()?.push(screen)

    fun pop() = getView()?.pop()

    fun updateTitle(title: CharSequence) = getView()?.updateTitle(title)

    fun setLoadingSpinner(show: Boolean) = getView()?.setLoadingSpinner(show)

    fun isTopScreen(clazz: Class<*>): Boolean {
        return backStack.peek().screen.javaClass == clazz
    }

    override fun viewAttached() {
    }

    override fun viewDetached() {
    }

    interface View {

        fun push(screen: Screen<*>)
        fun pop()
        fun updateTitle(title: CharSequence)
        fun setLoadingSpinner(show: Boolean)
    }
}