package com.kilomobi.aviv.data.remote

import com.squareup.moshi.Json

data class RemoteProperties(
    @Json(name = "items") val properties: List<RemoteProperty>?,
    @Json(name = "totalCount") val totalCount: Int
)