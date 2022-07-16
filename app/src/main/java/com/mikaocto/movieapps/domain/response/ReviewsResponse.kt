package com.mikaocto.movieapps.domain.response

import android.os.Build
import android.text.Html
import com.mikaocto.movieapps.data.Review

data class ReviewsResponse(
    val id: Int,
    val page: Int,
    val results: List<ReviewResult>,
    val total_pages: Int,
    val total_results: Int
)

data class ReviewResult(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
) {
    fun toReviewData() = Review(
        authorName = author_details.name.ifEmpty { author },
        avatar_path = author_details.avatar_path.orEmpty(),
        content = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(content)
        },
        rating = author_details.rating?.toFloat() ?: 0f
    )
}

data class AuthorDetails(
    val avatar_path: String?,
    val name: String,
    val rating: Double?,
    val username: String
)