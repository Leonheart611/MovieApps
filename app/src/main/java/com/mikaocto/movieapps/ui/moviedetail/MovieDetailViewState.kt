package com.mikaocto.movieapps.ui.moviedetail

import com.mikaocto.movieapps.data.MovieDetail

sealed class MovieDetailViewState {
    class OnSuccessGetMovieDetail(val data: MovieDetail) : MovieDetailViewState()
    class OnFailureGetMovieDetail(val message: String) : MovieDetailViewState()
}
