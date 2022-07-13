package com.mikaocto.movieapps.repository

import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.domain.API
import com.mikaocto.movieapps.domain.response.Genre
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
            throw Error(e)
        }
    }

    override suspend fun getDiscoverMovie(withGenres: Int?, page: Int): Flow<MutableList<Movie>> =
        flow {
            try {
                val value =
                    withGenres?.let { api.getDiscoverMovie(it, page) } ?: api.getDiscoverMovie(page)
                if (value.isSuccessful) {
                    val result = value.body()?.results?.map { result -> result.toMovieData() }
                    result?.let { emit(it.toMutableList()) }
                } else {
                    throw Error(value.errorBody().toString())
                }
            } catch (e: Exception) {
                throw Error(e)
            }

        }
}