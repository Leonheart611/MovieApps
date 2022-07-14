package com.mikaocto.movieapps.ui.genrelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mikaocto.movieapps.R
import com.mikaocto.movieapps.databinding.FragmentGenreListBinding
import com.mikaocto.movieapps.ui.adapter.GenreListAdapter
import com.mikaocto.movieapps.util.makeToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreListFragment : Fragment(), GenreListAdapter.OnGenreClicklistener {

    private val viewModel: GenreListViewModel by viewModels()

    private var _binding: FragmentGenreListBinding? = null
    private val binding get() = _binding!!
    private val genreListAdapter = GenreListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenreListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.rvGenreList) {
            layoutManager = GridLayoutManager(context, 2)
            adapter = genreListAdapter
        }

        viewModel.getAllGenre()
        viewModel.genreViewState.observe(viewLifecycleOwner) {
            when (it) {
                is GenreViewState.OnFailureGetGenre -> {
                    binding.pbGenreList.isVisible = false
                    context?.makeToast(it.message)
                }
                is GenreViewState.OnSuccessGetGenre -> {
                    binding.pbGenreList.isVisible = false
                    genreListAdapter.submitList(it.data)
                }
            }

        }


    }

    override fun onGenreClicklistener(id: Int) {

    }


}