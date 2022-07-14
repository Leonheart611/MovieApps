package com.mikaocto.movieapps.util

import com.mikaocto.movieapps.domain.response.Genre

object DataHelper {

    val genreList = mutableListOf(
        Genre(name = "Horor", id = 1),
        Genre(name = "Action", id = 2),
        Genre(name = "Sci-fi", id = 3),
        Genre(name = "Family", id = 4),
    )
}