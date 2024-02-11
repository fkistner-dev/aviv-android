package com.kilomobi.aviv.data

import com.kilomobi.aviv.data.di.IoDispatcher
import com.kilomobi.aviv.data.remote.AvivApiService
import com.kilomobi.aviv.domain.Property
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject

class PropertiesRepository @Inject constructor(
    private val restInterface: AvivApiService,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    private var _properties = listOf<Property>()

    val properties: List<Property>
        get() = _properties

    suspend fun loadProperties() {
        return withContext(Dispatchers.IO) {
            try {
                val remoteProperties = restInterface.getProperties()
                _properties = remoteProperties.properties.map {
                    Property(
                        it.bedrooms,
                        it.city,
                        it.id,
                        it.area,
                        it.imageUrl,
                        it.price,
                        it.professional,
                        it.propertyType,
                        it.offerType,
                        it.rooms
                    )
                }
            } catch (e: Exception) {
                when (e) {
                    is UnknownHostException,
                    is ConnectException,
                    is HttpException -> {
                        throw Exception("Something went wrong. We have no data.")
                    }

                    else -> throw e
                }
            }
        }
    }
}