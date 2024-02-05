package com.idaxmx.moviedemo.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.idaxmx.moviedemo.R

class ShimmerMovie(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    private val binding: ViewDataBinding? = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.widget_movie_load,
        this,
        true
    )
}