package com.mikaocto.movieapps.repository.paggination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mikaocto.movieapps.data.Review
import com.mikaocto.movieapps.domain.API
import okio.IOException
import retrofit2.HttpException

class ReviewsPaggination(private val api: API, private val movieId: Int) :
    PagingSource<Int, Review>() {

    override fun getRefreshKey(state: PagingState<Int, Review>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Review> {
        val position = params.key ?: START_PAGE_INDEX

        return try {
            val response = api.getMovieReviews(movieId = movieId, page = position)
            val result =
                response.body()?.results?.map { result -> result.toReviewData() } ?: mutableListOf()
            if (response.isSuccessful)
                LoadResult.Page(
                    data = result,
                    prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                    nextKey = if (result.isEmpty()) null else position + 1
                )
            else LoadResult.Error(Throwable("${response.code()}, ${response.message()}"))
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val START_PAGE_INDEX = 1
    }
}