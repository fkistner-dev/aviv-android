package com.kilomobi.aviv.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kilomobi.aviv.data.remote.AvivApiService
import com.kilomobi.aviv.data.remote.RemoteProperties
import com.kilomobi.aviv.domain.Property
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject constructor(
    // TODO : later DI
) : ViewModel() {

    private var restInterface: AvivApiService

    init {
        val moshi =
            Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://gsl-apps-technical-test.dignp.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        restInterface = retrofit.create(AvivApiService::class.java)
    }

    private val _state = mutableStateOf(
        PropertiesScreenState(
            properties = emptyList(),
            isLoading = true,
            areaFilter = 100f
        )
    )

    val state: State<PropertiesScreenState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    init {
        loadProperties()
    }

    fun loadProperties() {
        viewModelScope.launch(errorHandler) {
            val remoteList = restInterface.getProperties()
            if (remoteList.properties?.isNotEmpty() == true) {
                _state.value = _state.value.copy(
                    properties = remoteList.properties.map { remoteProperty ->
                        Property(
                            remoteProperty.bedrooms,
                            remoteProperty.city,
                            remoteProperty.id,
                            remoteProperty.area,
                            remoteProperty.imageUrl,
                            remoteProperty.price,
                            remoteProperty.professional,
                            remoteProperty.propertyType,
                            remoteProperty.offerType,
                            remoteProperty.rooms
                        )
                    },
                    isLoading = false,
                    error = null
                )
            } else {
                // TODO : error fetching
            }
        }
    }

    fun updateFilterValue(filterValue: Float) {
        _state.value = _state.value.copy(
            areaFilter = filterValue
        )
    }
}