package com.challenge.domain.usecases

import com.challenge.common.error.ErrorHandler
import com.challenge.common.error.RemoteError
import com.challenge.common.state.ErrorState
import com.challenge.common.state.Loaded
import com.challenge.domain.model.GitRepoModel
import com.challenge.domain.repository.GitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class GetRepoUseCaseTest : BaseTest() {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    private val getRepoUseCase: GetRepoUseCase by inject()
    private val gitRepository: GitRepository by inject()

    @Before
    fun setup() {
        declareMock<GitRepository>()
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun shouldCallRepositoryAndReturnData() = runBlockingTest {
        val page = 1
        Mockito.`when`(gitRepository.getRepo(page)).thenReturn(Loaded(getMockedList()))

        val result = getRepoUseCase.invoke(page) as Loaded<List<GitRepoModel>>

        Mockito.verify(gitRepository).getRepo(page)
        Assert.assertEquals(result.result, getMockedList())
    }

    private fun getMockedList(): List<GitRepoModel> {
        return listOf(
            GitRepoModel(
                name = "Name",
                description = "desc",
                nameOwner = "Sam",
                imageOwner = "",
                numberStars = 10,
                forks = 15
            )
        )
    }
}