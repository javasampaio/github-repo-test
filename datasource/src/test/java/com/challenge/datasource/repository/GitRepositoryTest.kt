package com.challenge.datasource.repository

import com.challenge.common.state.Loaded
import com.challenge.datasource.BaseTest
import com.challenge.datasource.data.GitDatasource
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
@ExperimentalCoroutinesApi
class GitRepositoryTest: BaseTest() {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    private val gitDatasource: GitDatasource by inject()
    private val gitRepository: GitRepository by inject()

    @Before
    fun setup() {
        declareMock<GitDatasource>()
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
        Mockito.`when`(gitDatasource.getRepo(page)).thenReturn(emptyList())

        val result = gitRepository.getRepo(page) as Loaded<List<GitRepoModel>>

        Mockito.verify(gitDatasource).getRepo(page)
        Assert.assertEquals(result.result, emptyList<GitRepoModel>())
    }
}