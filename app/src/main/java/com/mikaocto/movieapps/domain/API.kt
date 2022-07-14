package com.mikaocto.movieapps.domain

import com.mikaocto.movieapps.domain.response.GenresResponse
import com.mikaocto.movieapps.domain.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface API {
    companion object{
        const val KEY = "710a60c4c71d139a11ec46e367b66943"
    }

    @GET("genre/movie/list")
    suspend fun getOfficialGenres(@Query("api_key") apiKey: String = KEY ): Response<GenresResponse>

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query("with_genres") withGenres: Int,
        @Query("page") page: Int
    ): Response<MovieListResponse>

    @GET("discover/movie")
    suspend fun getDiscoverMovie(@Query("page") page: Int): Response<MovieListResponse>

}