package com.mikaocto.movieapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mikaocto.movieapps.databinding.GenreItemListBinding
import com.mikaocto.movieapps.domain.response.Genre

class GenreListAdapter(val listener: OnGenreClicklistener) :
    ListAdapter<Genre, GenreListAdapter.GenreListHolder>(GenreDiffUtil()) {

    interface OnGenreClicklistener {
        fun onGenreClicklistener(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreListHolder {
        return GenreListHolder(
            GenreItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreListHolder, position: Int) {
        getItem(position).let { holder.bind(it) }
    }


    inner class GenreListHolder(private val binding: GenreItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Genre) {
            binding.tvGenreName.text = data.name
            binding.root.setOnClickListener {
                listener.onGenreClicklistener(data.id)
            }
        }
    }


    class GenreDiffUtil : DiffUtil.ItemCallback<Genre>() {
        override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
            return oldItem == newItem
        }
    }


}