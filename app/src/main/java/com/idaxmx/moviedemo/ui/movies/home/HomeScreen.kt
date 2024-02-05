package com.idaxmx.moviedemo.ui.movies.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.idaxmx.moviedemo.R
import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.databinding.FragmentHomeBinding
import com.idaxmx.moviedemo.ui.movies.details.DetailsScreen.Companion.MOVIE_KEY
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeScreen : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        lifecycleScope.launchWhenCreated {
            launch { viewModel.state.collect(::render) }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@HomeScreen
            setHandleMovieSelected(::goToDetails)
            setHandleLogout(::handleLogout)
        }
    }

    private fun handleLogout() {
        viewModel.deleteUser(requireContext())
        goToLogin()
    }

    private fun goToDetails(movie: Movie) {
        Log.d("HomeScreen", "movieId: ${movie.id}")
        findNavController().navigate(
            R.id.action_homeScreen_to_detailsScreen,
            bundleOf(MOVIE_KEY to movie.id)
        )
    }

    private fun render(state: HomeState) {
        binding.state = state
    }

    private fun goToLogin() {
        findNavController().popBackStack(R.id.homeScreen, true)
        findNavController().navigate(R.id.loginScreen)
    }
}