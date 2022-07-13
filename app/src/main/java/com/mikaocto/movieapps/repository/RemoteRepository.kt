package com.mikaocto.movieapps.repository

import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.domain.response.MovieListResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteRepository {
    suspend fun getAllGenre(): Flow<MutableList<Genre>>
    suspend fun getDiscoverMovie(withGenres: Int?, page: Int = 1): Flow<MutableList<Movie>>
}