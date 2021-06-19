package com.challenge.domain.usecases

import com.challenge.domain.di.DomainDI
import org.junit.After
import org.junit.Rule
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

abstract class BaseTest : KoinTest {

    @get:Rule
    val koinRule = KoinTestRule.create {
        modules(modules = DomainDI.module)
    }

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @After
    fun autoClose() {
        stopKoin()
    }
}