package com.mikaocto.movieapps.util

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mikaocto.movieapps.data.Movie

class MoviePagginationHelperTest : PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return LoadResult.Page(
            data = DataHelper.movieList,
            prevKey = 1,
            nextKey = 1,
            itemsAfter = 1,
            itemsBefore = 1
        )
    }

}