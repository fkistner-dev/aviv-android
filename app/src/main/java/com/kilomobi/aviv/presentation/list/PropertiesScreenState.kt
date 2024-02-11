package com.kilomobi.aviv.presentation.list

import com.kilomobi.aviv.domain.Property

data class PropertiesScreenState(
    val properties: List<Property>,
    val isLoading: Boolean,
    val error: String? = null,
    val filterAsc: Boolean,
)