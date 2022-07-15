package com.mikaocto.movieapps.data

import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.domain.response.VideoResult

data class MovieDetail(
    val title: String,
    val overview: String,
    val poster_path: String,
    val tagline: String,
    val videos: List<VideoResult>,
    val vote: Double,
    val release_date: String,
    val backdropImage: String
)
