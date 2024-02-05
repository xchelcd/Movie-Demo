package com.idaxmx.moviedemo.ui.movies

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.idaxmx.moviedemo.R
import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.databinding.CellMovieBinding

class MovieItem(
    context: Context,
    attrs: AttributeSet?
) : LinearLayout(context, attrs) {

    private val binding: CellMovieBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.cell_movie,
        this,
        true
    )

    var data: Movie? = null
        set(value) {
            field = value
            binding.movie = field
        }

}