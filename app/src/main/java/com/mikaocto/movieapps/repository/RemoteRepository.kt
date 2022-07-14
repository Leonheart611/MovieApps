package com.mikaocto.movieapps.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.domain.response.Genre
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun getAllGenre(): Flow<MutableList<Genre>>
    fun getDiscoverMovie(withGenres: Int?): LiveData<PagingData<Movie>>
}