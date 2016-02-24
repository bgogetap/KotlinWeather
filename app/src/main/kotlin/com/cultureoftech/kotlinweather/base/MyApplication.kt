package com.cultureoftech.kotlinweather.base

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.cultureoftech.kotlinweather.BuildConfig
import com.jakewharton.threetenabp.AndroidThreeTen
import io.fabric.sdk.android.Fabric
import timber.log.Timber

/**
 * Created by bgogetap on 2/16/16.
 */
class MyApplication : Application() {

    companion object {
        lateinit var component: MyApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerMyApplicationComponent.builder()
                .myApplicationModule(MyApplicationModule(this))
                .build()
        Fabric.with(this, Crashlytics());
        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
