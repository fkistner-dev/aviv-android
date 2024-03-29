package com.kilomobi.aviv.presentation

import com.kilomobi.aviv.data.remote.RemoteProperties
import com.kilomobi.aviv.data.remote.RemoteProperty
import com.kilomobi.aviv.domain.Property

object MockContent {
    fun getProperty() = Property(
        bedrooms = 4,
        city = "Villers-sur-Mer",
        id = 1,
        area = 250.0,
        imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
        price = 1500000.0,
        professional = "GSL EXPLORE",
        propertyType = "Maison - Villa",
        offerType = 1,
        rooms = 8
    )

    fun getRemoteProperty() = RemoteProperty(
        bedrooms = 4,
        city = "Villers-sur-Mer",
        id = 1,
        area = 250.0,
        imageUrl = "https://v.seloger.com/s/crop/590x330/visuels/1/7/t/3/17t3fitclms3bzwv8qshbyzh9dw32e9l0p0udr80k.jpg",
        price = 1500000.0,
        professional = "GSL EXPLORE",
        propertyType = "Maison - Villa",
        offerType = 1,
        rooms = 8
    )

    fun getRemoteProperties() = RemoteProperties(
        properties = listOf(getRemoteProperty(), getRemoteProperty()),
        totalCount = 2
    )
}