package com.idaxmx.moviedemo.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.forEach
import androidx.databinding.DataBindingUtil
import com.idaxmx.moviedemo.R
import com.idaxmx.moviedemo.data.model.Movie
import com.idaxmx.moviedemo.databinding.WidgetMovieDetailsBinding

class MovieDetails(
    context: Context,
    attrs: AttributeSet?,
) : LinearLayout(context, attrs) {

    private val binding: WidgetMovieDetailsBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.widget_movie_details,
        this,
        true
    )

    var data: Movie? = null
        set(value) {
            field = value
            if (value == null) return
            binding.movie = value
            setStarRate(field)
        }

    private fun setStarRate(movie: Movie?) {
        for (i in 0..4) {
            if (i < movie!!.voteAverage!!.toInt() / 2) {
                val starFilled = ImageView(context).apply {
                    setBackgroundResource(R.drawable.ic_star_fill)
                }
                binding.starRateLayout.addView(starFilled)
            } else {
                val starFilled = ImageView(context).apply {
                    setBackgroundResource(R.drawable.ic_star_border)
                }
                binding.starRateLayout.addView(starFilled)
            }
        }
    }
}