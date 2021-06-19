package com.challenge.datasource.data


import com.challenge.datasource.BaseTest
import com.challenge.datasource.client.GitClient
import com.challenge.datasource.response.RepoResponse
import com.challenge.domain.model.GitRepoModel
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
@ExperimentalCoroutinesApi
class GitRemoteDatasourceTest: BaseTest() {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    private val gitClient: GitClient by inject()
    private val gitDatasource: GitDatasource by inject()

    @Before
    fun setup() {
        declareMock<GitClient>()
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineScope.cleanupTestCoroutines()
    }

    @Test
    fun shouldCallDatasourceAndReturnEmptyList() = runBlockingTest {
        val page = 1
        Mockito.`when`(gitClient.getRepo(page)).thenReturn(RepoResponse(emptyList()))

        val result = gitDatasource.getRepo(page)

        Mockito.verify(gitClient).getRepo(page)
        Assert.assertEquals(result, emptyList<GitRepoModel>())
    }
}