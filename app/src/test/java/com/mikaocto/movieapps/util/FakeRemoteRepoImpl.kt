package com.mikaocto.movieapps.util

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.domain.API
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteRepoImpl(val api: API) : RemoteRepository {
    override suspend fun getAllGenre(): Flow<MutableList<Genre>> = flow {
        emit(DataHelper.genreList)
    }

    override fun getDiscoverMovie(withGenres: Int?): LiveData<PagingData<Movie>> {
        TODO("Not yet implemented")
    }
}