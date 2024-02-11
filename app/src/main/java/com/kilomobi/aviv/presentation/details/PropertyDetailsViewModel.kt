package com.kilomobi.aviv.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kilomobi.aviv.data.remote.AvivApiService
import com.kilomobi.aviv.domain.Property
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

@HiltViewModel
class PropertyDetailsViewModel @Inject constructor(
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
        PropertyDetailsScreenState(
            isLoading = true
        )
    )

    val state: State<PropertyDetailsScreenState>
        get() = _state

    private val errorHandler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _state.value = _state.value.copy(
            error = exception.message,
            isLoading = false
        )
    }

    fun loadProperty(id: Int) {
        viewModelScope.launch(errorHandler) {
            val remoteProperty = restInterface.getProperty(id)
            _state.value = _state.value.copy(
                property = Property(
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
                ),
                isLoading = false,
                error = null
            )
        }
    }
}