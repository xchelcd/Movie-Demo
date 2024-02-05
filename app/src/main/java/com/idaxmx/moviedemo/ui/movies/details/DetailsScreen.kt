package com.idaxmx.moviedemo.ui.movies.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.idaxmx.moviedemo.R
import com.idaxmx.moviedemo.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailsScreen : Fragment() {

    companion object {
        val MOVIE_KEY = "MOVIE_ID_KEY"
    }

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    private var movieId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieId = arguments?.getLong(MOVIE_KEY)
        if (movieId == null) {
            findNavController().popBackStack()
            return
        }
        viewModel.fetchMovie(movieId!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(layoutInflater)
        lifecycleScope.launchWhenCreated {
            launch { viewModel.state.collect(::render) }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@DetailsScreen
        }
    }

    private fun render(state: DetailsState) {
        binding.state = state
    }
}