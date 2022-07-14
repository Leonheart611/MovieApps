package com.mikaocto.movieapps.ui.genrelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mikaocto.movieapps.di.Module
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.util.DataHelper
import com.mikaocto.movieapps.util.FakeRemoteRepoImpl
import com.mikaocto.movieapps.util.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class GenreListViewModelTest {
    @get:Rule
    val instantExecutionRule = InstantTaskExecutorRule()

    private lateinit var viewModel: GenreListViewModel

    @Before
    fun setupViewModel() {
        val remoteRepository = FakeRemoteRepoImpl()
        viewModel = GenreListViewModel(remoteRepository)
    }


    @Test
    fun `test getAllGenre should return return GenreList`() {
        val result = mutableListOf<Genre>()
        viewModel.getAllGenre()
        val viewState = viewModel.genreViewState.getOrAwaitValue()
        when (viewState) {
            is GenreViewState.OnSuccessGetGenre -> {
                 result.addAll(viewState.data)
            }
            else -> {}
        }

        assertThat(result, `is`(DataHelper.genreList))
    }

}