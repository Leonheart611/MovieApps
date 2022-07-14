package com.mikaocto.movieapps.ui.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mikaocto.movieapps.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {

    private val genreId by lazy { MutableLiveData(0) }

    val movieListLiveData = genreId.switchMap {
        remoteRepository.getDiscoverMovie(it)
    }.cachedIn(viewModelScope)

    fun updateGenreId(id: Int) {
        genreId.value = id
    }

}