package com.mikaocto.movieapps.ui.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.mikaocto.movieapps.databinding.FragmentMovieListBinding
import com.mikaocto.movieapps.ui.adapter.MovieListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(), MovieListAdapter.MovieOnClicklistener {

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()
    private val args: MovieListFragmentArgs by navArgs()
    private val movieListAdapter = MovieListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        viewModel.updateGenreId(args.genreId)
        viewModel.movieListLiveData.observe(viewLifecycleOwner) {
            movieListAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    private fun setupView() {
        with(binding) {
            tbMovieList.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
            tbMovieList.title = args.genreName

            rvMovieList.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = movieListAdapter
            }

        }
    }


    override fun onMovieClicklistener(movieId: Int) {

    }

}