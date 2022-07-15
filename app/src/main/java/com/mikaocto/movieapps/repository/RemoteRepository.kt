package com.mikaocto.movieapps.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.data.MovieDetail
import com.mikaocto.movieapps.data.Review
import com.mikaocto.movieapps.domain.API
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.domain.response.MovieDetailResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteRepository {
    suspend fun getAllGenre(): Flow<MutableList<Genre>>
    fun getDiscoverMovie(withGenres: Int): LiveData<PagingData<Movie>>
    suspend fun getMovieDetail(movieId: Int): Flow<MovieDetail>
    fun getMovieReviews(movieId: Int): LiveData<PagingData<Review>>
}