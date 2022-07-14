package com.mikaocto.movieapps.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.domain.API
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.repository.paggination.MoviePaggination
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val api: API) : RemoteRepository {

    override suspend fun getAllGenre(): Flow<MutableList<Genre>> = flow {
        try {
            val value = api.getOfficialGenres()
            if (value.isSuccessful) {
                value.body()?.genres?.let { emit(it.toMutableList()) }
            } else {
                throw Error(value.errorBody().toString())
            }
        } catch (e: Exception) {
            e.stackTrace
            throw Error(e)
        }
    }

    override fun getDiscoverMovie(withGenres: Int?): LiveData<PagingData<Movie>> =
        Pager(config = PagingConfig(
            pageSize = 20,
            maxSize = 100,
            enablePlaceholders = false
        ), pagingSourceFactory = { MoviePaggination(api, withGenres) }
        ).liveData
}