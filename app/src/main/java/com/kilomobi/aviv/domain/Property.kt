package com.kilomobi.aviv.domain

data class Property(
    val bedrooms: Int?,
    val city: String,
    val id: Int,
    val area: Double,
    val imageUrl: String?,
    val price: Double?,
    val professional: String?,
    val propertyType: String?,
    val offerType: Int,
    val rooms: Int?
)