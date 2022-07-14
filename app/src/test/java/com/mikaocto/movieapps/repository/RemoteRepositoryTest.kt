package com.mikaocto.movieapps.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mikaocto.movieapps.util.DataHelper
import com.mikaocto.movieapps.util.FakeRemoteRepoImpl
import com.mikaocto.movieapps.util.collectData
import com.mikaocto.movieapps.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class RemoteRepositoryTest {
    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()
    private lateinit var remoteRepository: RemoteRepository

    val dispatcher = TestCoroutineDispatcher()

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setupRepository() {
        Dispatchers.setMain(dispatcher)
        remoteRepository = FakeRemoteRepoImpl()
    }


    @Test
    fun `get Genre from remote repository should return Genre List`() {
        runTest {
            val result = remoteRepository.getAllGenre().asLiveData()

            assertThat(result.getOrAwaitValue(), `is`(DataHelper.genreList))
        }
    }

    @Test
    fun `get Movie list from remote repository should return Movie List`() {
        runTest {
            val paging = remoteRepository.getDiscoverMovie(1)

            val result = paging.getOrAwaitValue()
            assertThat(
                result.collectData(),
                `is`(DataHelper.movieList)
            )
        }
    }

}