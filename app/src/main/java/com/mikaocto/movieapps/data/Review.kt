package com.mikaocto.movieapps.data

import android.text.Spanned

data class Review(
    val authorName: String,
    val avatar_path: String,
    val content: Spanned,
    val rating: Float
)
