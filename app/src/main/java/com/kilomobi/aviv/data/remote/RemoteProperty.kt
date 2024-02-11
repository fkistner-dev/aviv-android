package com.kilomobi.aviv.data.remote

import com.squareup.moshi.Json

data class RemoteProperty(
    @Json(name = "bedrooms") val bedrooms: Int?,
    @Json(name = "city") val city: String,
    @Json(name = "id") val id: Int,
    @Json(name = "area") val area: Double,
    @Json(name = "url") val imageUrl: String?,
    @Json(name = "price") val price: Double?,
    @Json(name = "professional") val professional: String,
    @Json(name = "propertyType") val propertyType: String?,
    @Json(name = "offerType") val offerType: Int,
    @Json(name = "rooms") val rooms: Int?
)