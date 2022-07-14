package com.mikaocto.movieapps.ui.genrelist

import com.mikaocto.movieapps.domain.response.Genre

sealed class GenreViewState {
    class OnSuccessGetGenre(val data: MutableList<Genre>) : GenreViewState()
    class OnFailureGetGenre(val message: String) : GenreViewState()
}
