package com.mikaocto.movieapps.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mikaocto.movieapps.data.Review
import com.mikaocto.movieapps.databinding.ReviewItemListBinding
import com.mikaocto.movieapps.util.loadAvatar

class ReviewListAdapter :
    PagingDataAdapter<Review, ReviewListAdapter.ReviewViewHolder>(ReviewDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            ReviewItemListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    class ReviewViewHolder(private val viewBinding: ReviewItemListBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Review) {
            with(viewBinding) {
                tvReviewContent.text = data.content
                ivReviewPict.loadAvatar(data.avatar_path)
                tvReviewName.text = data.authorName
                tvReviewRating.text = data.rating.toString()
            }
        }
    }


    class ReviewDiffUtil : DiffUtil.ItemCallback<Review>() {
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.authorName == newItem.authorName
        }

        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }
    }
}