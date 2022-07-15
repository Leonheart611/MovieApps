package com.mikaocto.movieapps.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.mikaocto.movieapps.R

fun Context.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w500/$url")
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadAvatar(url: String) {
    Glide.with(this.context)
        .load("https://image.tmdb.org/t/p/w500/$url")
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}