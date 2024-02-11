package com.kilomobi.aviv.presentation.details

import com.kilomobi.aviv.domain.Property

data class PropertyDetailsScreenState(
    val property: Property? = null,
    val isLoading: Boolean,
    val error: String? = null,
)