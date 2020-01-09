package com.bryonnabaines.urbandictionaryapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * created by bryonnabaines on 2020-01-07
 */

interface Api {
    @Headers("X-RapidAPI-Key: 51f87ea179mshdaae434b78b77a3p12583ajsn21644b7a6f8c")

    @GET("define")
    fun define(@Query("term") term: String) : Observable<WordListResponse>
}