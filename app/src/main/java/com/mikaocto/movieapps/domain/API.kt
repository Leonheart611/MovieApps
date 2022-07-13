package com.mikaocto.movieapps.domain

import com.mikaocto.movieapps.domain.response.GenresResponse
import com.mikaocto.movieapps.domain.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("/genre/movie/list")
    fun getOfficialGenres(): Response<GenresResponse>

    @GET("/discover/movie")
    fun getDiscoverMovie(
        @Path("with_genres") withGenres: Int,
        @Path("page") page: Int
    ): Response<MovieListResponse>

    @GET("/discover/movie")
    fun getDiscoverMovie(@Path("page") page: Int): Response<MovieListResponse>

}