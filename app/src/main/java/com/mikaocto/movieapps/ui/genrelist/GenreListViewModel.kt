package com.mikaocto.movieapps.ui.genrelist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreListViewModel @Inject constructor(private val remoteRepository: RemoteRepository) :
    ViewModel() {

    private val _genreViewState = MutableLiveData<GenreViewState>()
    val genreViewState: LiveData<GenreViewState> by lazy { _genreViewState }

    fun getAllGenre() {
        viewModelScope.launch {
            remoteRepository.getAllGenre().catch { e ->
                _genreViewState.postValue(GenreViewState.OnFailureGetGenre(e.message.toString()))
            }.collect {
                _genreViewState.postValue(GenreViewState.OnSuccessGetGenre(it))
            }
        }

    }
}