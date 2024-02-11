package com.kilomobi.aviv

import com.kilomobi.aviv.data.PropertiesRepository
import com.kilomobi.aviv.domain.usecase.GetInitialPropertiesUseCase
import com.kilomobi.aviv.presentation.MockContent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPropertiesUseCaseTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    // Setup useCase
    private val propertiesRepository = PropertiesRepository(
        FakeApiService(),
        dispatcher
    )

    @Test
    fun getProperties_fromApi() = scope.runTest {
        propertiesRepository.loadProperties()
        val useCase = GetInitialPropertiesUseCase(propertiesRepository)
        advanceUntilIdle()

        // Execute useCase
        val propertiesList = MockContent.getRemoteProperties()
        val propertiesReceived = useCase()
        advanceUntilIdle()

        // Assertion
        assert(propertiesList.totalCount == propertiesReceived.count())
    }
}