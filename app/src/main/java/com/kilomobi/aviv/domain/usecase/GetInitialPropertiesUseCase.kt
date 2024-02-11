package com.kilomobi.aviv.domain.usecase

import com.kilomobi.aviv.data.PropertiesRepository
import com.kilomobi.aviv.domain.Property
import javax.inject.Inject

class GetInitialPropertiesUseCase @Inject constructor(
    private val repository: PropertiesRepository
) {
    suspend operator fun invoke(): List<Property> {
        repository.loadProperties()
        return repository.properties
    }
}