package com.kilomobi.aviv

import com.kilomobi.aviv.data.remote.AvivApiService
import com.kilomobi.aviv.data.remote.RemoteProperties
import com.kilomobi.aviv.data.remote.RemoteProperty
import com.kilomobi.aviv.presentation.MockContent
import kotlinx.coroutines.delay

class FakeApiService : AvivApiService {

    override suspend fun getProperties(): RemoteProperties {
        delay(1000)
        return MockContent.getRemoteProperties()
    }

    override suspend fun getProperty(id: Int): RemoteProperty {
        delay(1000)
        return MockContent.getRemoteProperty()
    }
}