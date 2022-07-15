package com.mikaocto.movieapps.ui.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikaocto.movieapps.R
import com.mikaocto.movieapps.data.MovieDetail
import com.mikaocto.movieapps.databinding.FragmentMovieDetailBinding
import com.mikaocto.movieapps.domain.response.Genre
import com.mikaocto.movieapps.ui.adapter.GenreListAdapter
import com.mikaocto.movieapps.ui.adapter.ReviewListAdapter
import com.mikaocto.movieapps.util.loadImage
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailFragment : Fragment(){

    private val viewModel: MovieDetailViewModel by viewModels()
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val reviewListAdapter = ReviewListAdapter()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setObseverable()
        viewModel.getMovieDetail(args.movieId)
    }


    private fun setObseverable() {
        viewModel.movieDetail.observe(viewLifecycleOwner) {
            when (it) {
                is MovieDetailViewState.OnFailureGetMovieDetail -> {

                }
                is MovieDetailViewState.OnSuccessGetMovieDetail -> {
                    setViewValue(it.data)
                }
            }
        }

        viewModel.reviewsLiveData.observe(viewLifecycleOwner) {
            reviewListAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setViewValue(data: MovieDetail) {
        with(binding.headerView) {
            tvDetailOverview.text = data.overview
            tvDetailTitle.text = data.title
            tvDetailTagline.text = getString(R.string.movie_detail_tagline, data.tagline)
            tvDetailVote.text = data.vote.toString()
            tvDetailRelease.text = getString(R.string.movie_detail_release_date, data.release_date)
            ivDetailPoster.loadImage(data.poster_path)
            ivDetailBackdrop.loadImage(data.backdropImage)
        }
        binding.youtubeMoviePlayer.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                val officialVideo = data.videos.find { videoResult -> videoResult.official }
                youTubePlayer.loadVideo(officialVideo?.key ?: "", 0F)
            }
        })
    }

    private fun setupView() {
        with(binding) {
            tbDetail.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            rvDetailReview.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = reviewListAdapter
            }
            lifecycle.addObserver(youtubeMoviePlayer)
        }
    }


}