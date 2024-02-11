package com.kilomobi.aviv.domain.usecase

import com.kilomobi.aviv.domain.Property
import javax.inject.Inject

class GetFilteredPropertiesUseCase @Inject constructor() {
    operator fun invoke(sortAsc: Boolean, properties: List<Property>): List<Property> {
        return if (sortAsc) properties.sortedBy { it.area } else properties.sortedByDescending { it.area }
    }
}