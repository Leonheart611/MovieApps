package com.mikaocto.movieapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mikaocto.movieapps.data.Movie
import com.mikaocto.movieapps.databinding.MovieItemListBinding
import com.mikaocto.movieapps.util.loadImage

class MovieListAdapter(val clicklistener: MovieOnClicklistener) :
    PagingDataAdapter<Movie, MovieListAdapter.MovieHolder>(MovieListDiffUtil()) {

    interface MovieOnClicklistener {
        fun onMovieClicklistener(movieId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        return MovieHolder(
            MovieItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class MovieHolder(private val viewBinding: MovieItemListBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Movie) {
            with(viewBinding) {
                tvMovieTitle.text = data.title
                tvMovieReleaseDate.text = data.release_date
                tvMovieVote.text = data.vote.toString()
                ivMoviePoster.loadImage(data.poster_path)
                root.setOnClickListener {
                    clicklistener.onMovieClicklistener(data.id)
                }
            }
        }
    }


    class MovieListDiffUtil : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}