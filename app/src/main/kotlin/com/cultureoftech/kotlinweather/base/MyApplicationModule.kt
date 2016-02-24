package com.cultureoftech.kotlinweather.base

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.cultureoftech.kotlinweather.R
import com.cultureoftech.kotlinweather.retrofit.AuthInterceptor
import com.cultureoftech.kotlinweather.ui.BackStack
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by bgogetap on 2/16/16.
 */
@Module
class MyApplicationModule(private val app: MyApplication) {

    private val baseUrl = "http://api.openweathermap.org/data/2.5/"

    @Provides @Singleton fun provideAppContext(): Context = app

    @Provides @Singleton fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }

    @Provides @Singleton fun provideBackStack(): BackStack {
        return BackStack()
    }

    @Provides @Singleton fun provideRetrofit(client: OkHttpClient): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit
    }

    @Provides @Singleton fun provideOkHttp(context: Context): OkHttpClient {
        val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(context.getString(R.string.open_weather_map_api_key)))
                .build()
        return client
    }
}