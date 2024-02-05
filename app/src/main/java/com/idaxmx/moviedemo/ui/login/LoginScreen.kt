package com.idaxmx.moviedemo.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth
import com.idaxmx.moviedemo.R
import com.idaxmx.moviedemo.databinding.FragmentLoginBinding
import com.idaxmx.moviedemo.ui.login.register.RegisterDialog
import com.idaxmx.moviedemo.util.Const
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginScreen : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        lifecycleScope.launchWhenCreated {
            launch { viewModel.state.collect(::render) }
        }
        auth = FirebaseAuth.getInstance()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@LoginScreen
            setHandleLogin(::submitLogin)
            setHandleRegister(::handleSignUp)
            setHandlePassword(viewModel::setPassword)
            setHandleUsername(viewModel::setUsername)
        }
    }

    private fun submitLogin() {
        viewModel.submitLogin {
            viewModel.saveUserState(requireContext())
            goToHome()
        }
    }


    private fun handleSignUp() {
        RegisterDialog(auth) { _ ->
            viewModel.saveUserState(requireContext())
            Toast.makeText(requireContext(), "User registered", Toast.LENGTH_SHORT).show()
            goToHome()
        }.show(parentFragmentManager, "RegisterDialog")
    }

    private fun render(state: LoginState) {
        binding.state = state
    }

    override fun onStart() {
        super.onStart()
        if (PreferenceManager.getDefaultSharedPreferences(requireContext())
                .getBoolean(Const.USER_LOGGED_KEY, false)
        )
            goToHome()
    }

    private fun goToHome() {
        findNavController().popBackStack(R.id.loginScreen, true)
        findNavController().navigate(R.id.homeScreen)
    }
}