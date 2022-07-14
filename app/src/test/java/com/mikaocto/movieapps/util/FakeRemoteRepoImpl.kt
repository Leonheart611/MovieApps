package com.mikaocto.movieapps.util

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.domain.API
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.repository.RemoteRepository
import com.mikaocto.movieapps.repository.paggination.MoviePaggination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteRepoImpl : RemoteRepository {
    override suspend fun getAllGenre(): Flow<MutableList<Genre>> = flow {
        emit(DataHelper.genreList)
    }

    override fun getDiscoverMovie(withGenres: Int?): LiveData<PagingData<Movie>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ), pagingSourceFactory = { MoviePagginationHelperTest() }
        ).liveData
    }
}