package com.mikaocto.movieapps.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mikaocto.movieapps.di.Module
import com.mikaocto.movieapps.util.DataHelper
import com.mikaocto.movieapps.util.FakeRemoteRepoImpl
import com.mikaocto.movieapps.util.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
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

    @Before
    fun setupRepository() {
        remoteRepository = FakeRemoteRepoImpl()
    }


    @Test
    fun `get Genre from remote repository should return Genre List`() {
        runTest {
            val result = remoteRepository.getAllGenre().asLiveData()

            assertThat(result.getOrAwaitValue(), `is`(DataHelper.genreList))
        }


    }

}