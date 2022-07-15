package com.mikaocto.movieapps.util

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.data.MovieDetail
import com.mikaocto.movieapps.data.Review
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.domain.response.MovieDetailResponse
import com.mikaocto.movieapps.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class FakeRemoteRepoImpl : RemoteRepository {
    override suspend fun getAllGenre(): Flow<MutableList<Genre>> = flow {
        emit(DataHelper.genreList)
    }

    override fun getDiscoverMovie(withGenres: Int): LiveData<PagingData<Movie>> {
        return Pager(config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ), pagingSourceFactory = { MoviePagginationHelperTest() }
        ).liveData
    }

    override suspend fun getMovieDetail(movieId: Int): Flow<MovieDetail> {
        TODO("Not yet implemented")
    }

    override fun getMovieReviews(movieId: Int): LiveData<PagingData<Review>> {
        TODO("Not yet implemented")
    }
}