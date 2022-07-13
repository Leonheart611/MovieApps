package com.mikaocto.movieapps.domain.response

import com.mikaocto.movieapps.data.Movie

data class MovieListResponse(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Result(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) {
    fun toMovieData(): Movie = Movie(
        id,
        original_language,
        original_title,
        overview,
        popularity,
        poster_path,
        release_date,
        title
    )
}