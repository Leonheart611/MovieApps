package com.mikaocto.movieapps.util

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.liveData
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.domain.response.Genre

object DataHelper {

    val genreList = mutableListOf(
        Genre(name = "Horor", id = 1),
        Genre(name = "Action", id = 2),
        Genre(name = "Sci-fi", id = 3),
        Genre(name = "Family", id = 4),
    )

    val movieList = mutableListOf(
        Movie(
            id = 1,
            poster_path = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
            vote = 90.0,
            release_date = "16-12-1997",
            title = "title1"
        ),
        Movie(
            id = 2,
            poster_path = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
            vote = 90.0,
            release_date = "16-12-1997",
            title = "title2"
        ),
        Movie(
            id = 3,
            poster_path = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
            vote = 90.0,
            release_date = "16-12-1997",
            title = "title3"
        ),
        Movie(
            id = 4,
            poster_path = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
            vote = 90.0,
            release_date = "16-12-1997",
            title = "title4"
        ),
        Movie(
            id = 5,
            poster_path = "/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg",
            vote = 90.0,
            release_date = "16-12-1997",
            title = "title5"
        )
    )

    val paggingExpected = Pager(config = PagingConfig(
        pageSize = 20,
        maxSize = 100,
        enablePlaceholders = false
    ), pagingSourceFactory = { MoviePagginationHelperTest() }
    ).liveData
}