package com.challenge.domain.usecases

import com.challenge.common.state.Loaded
import com.challenge.domain.model.GitPullModel
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
class GetPullUseCaseTest : BaseTest() {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    private val getPullUseCase: GetPullUseCase by inject()
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
        val author = "author"
        val repo = "repo"
        Mockito.`when`(gitRepository.getPulls(author, repo)).thenReturn(Loaded(getMockedList()))

        val result = getPullUseCase.invoke(author, repo) as Loaded<List<GitPullModel>>

        Mockito.verify(gitRepository).getPulls(author, repo)
        Assert.assertEquals(result.result, getMockedList())
    }

    private fun getMockedList(): List<GitPullModel> {
        return listOf(
            GitPullModel(
                namePr = "Name",
                titlePr = "title",
                datePr = "2021-06-22",
                bodyPr = "body",
                userImage = ""
            )
        )
    }
}