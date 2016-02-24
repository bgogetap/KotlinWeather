package com.cultureoftech.kotlinweather.ui

/**
 * Created by bgogetap on 2/18/16.
 */
abstract class PresenterRoot<T> {

    private var view: T? = null

    fun takeView(view: T) {
        if (view == null) throw NullPointerException("Taken view must not be null")
        if (this.view != view) {
            this.view = view
            viewAttached()
        }
    }

    fun dropView(view: T) {
        this.view == null
        viewDetached()
    }

    fun getView(): T? = view

    protected abstract fun viewAttached()

    protected abstract fun viewDetached()
}