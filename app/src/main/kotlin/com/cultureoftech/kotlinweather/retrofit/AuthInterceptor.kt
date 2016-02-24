package com.cultureoftech.kotlinweather.retrofit

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by bgogetap on 2/20/16.
 */
class AuthInterceptor(val apiKey: String): Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val url: HttpUrl = chain!!.request().url().newBuilder()
                .addQueryParameter("units", "imperial")
                .addQueryParameter("APPID", apiKey)
                .build()
        val request: Request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }
}