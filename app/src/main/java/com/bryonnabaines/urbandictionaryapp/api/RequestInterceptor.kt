package com.bryonnabaines.urbandictionaryapp.api

/**
 * created by bryonnabaines on 2020-01-08
 */


import android.app.Application
import android.content.Context
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.Response

@Singleton
class RequestInterceptor : Interceptor {
    private val context: Context?

    //Testing purposes only
    constructor() {
        this.context = null
    }


    @Inject
    constructor(application: Application) {
        this.context = application.applicationContext
    }


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {


        var originalRequest = chain.request()
            .newBuilder()
            .build()

        return chain.proceed(originalRequest)
    }
}
