package com.mikaocto.movieapps.data

data class Movie(
    val id: Int,
    val vote: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
)
