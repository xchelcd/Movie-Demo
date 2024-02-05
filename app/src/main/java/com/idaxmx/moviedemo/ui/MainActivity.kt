package com.idaxmx.moviedemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.shimmer.BuildConfig
import com.idaxmx.moviedemo.R
import com.idaxmx.moviedemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}