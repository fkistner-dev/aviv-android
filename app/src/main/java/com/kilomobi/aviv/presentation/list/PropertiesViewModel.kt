package com.kilomobi.aviv.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kilomobi.aviv.domain.usecase.GetFilteredPropertiesUseCase
import com.kilomobi.aviv.domain.usecase.GetInitialPropertiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject constructor(
    private val getInitialPropertiesUseCase: GetInitialPropertiesUseCase,
    private val getFilteredPropertiesUseCase: GetFilteredPropertiesUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(
        PropertiesScreenState(
            properties = emptyList(),
            isLoading = true,
            filterAsc = true
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
            val remoteList = getInitialPropertiesUseCase()
            if (remoteList.isNotEmpty()) {
                _state.value = _state.value.copy(
                    properties = remoteList,
                    isLoading = false,
                    error = null
                )
            } else {
                // TODO : error fetching
            }
        }
    }

    fun updateFilterValue() {
        val sortAsc = !_state.value.filterAsc
        val filteredProperties = getFilteredPropertiesUseCase(sortAsc, _state.value.properties)
        _state.value = _state.value.copy(
            properties = filteredProperties,
            filterAsc = sortAsc
        )
    }
}