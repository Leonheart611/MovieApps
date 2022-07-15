package com.mikaocto.movieapps.ui.moviedetail

import androidx.lifecycle.*
import com.mikaocto.movieapps.data.MovieDetail
import com.mikaocto.movieapps.repository.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(val remote: RemoteRepository) : ViewModel() {

    private val id by lazy { MutableLiveData(0) }
    private val _movieDetailViewState = MutableLiveData<MovieDetailViewState>()
    val movieDetail: LiveData<MovieDetailViewState> by lazy { _movieDetailViewState }

    val reviewsLiveData = id.switchMap {
        remote.getMovieReviews(it)
    }

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            id.value = movieId
            remote.getMovieDetail(movieId).catch {
                _movieDetailViewState.postValue(MovieDetailViewState.OnFailureGetMovieDetail(it.message.orEmpty()))
            }.collect {
                _movieDetailViewState.postValue(MovieDetailViewState.OnSuccessGetMovieDetail(it))
            }
        }


    }

}