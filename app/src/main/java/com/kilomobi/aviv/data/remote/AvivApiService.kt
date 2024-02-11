package com.kilomobi.aviv.data.remote

import retrofit2.http.GET

interface AvivApiService {
    @GET("listings.json")
    suspend fun getProperties(): RemoteProperties

    @GET("listings/{id}.json")
    suspend fun getProperty(id: Int): RemoteProperty
}