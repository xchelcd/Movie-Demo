package com.idaxmx.moviedemo.util.binding

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.google.android.material.textfield.TextInputEditText
import com.idaxmx.moviedemo.BuildConfig
import com.idaxmx.moviedemo.ui.movies.MovieList


@BindingAdapter("urlRounded")
fun urlRounded(v: ImageView, url: String?) {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1800) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
    val requestOptions = RequestOptions.bitmapTransform(RoundedCorners(20))
    Glide
        .with(v.context)
        .load("${BuildConfig.MOVIE_IMAGE_ROOT_URL}$url")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(shimmerDrawable)
        //.placeholder(R.drawable.ic_account)
        //.error(R.drawable.ic_account)
        //.circleCrop()
        .centerCrop()
        .apply(requestOptions)
        .into(v)
}

@BindingAdapter("url")
fun url(v: ImageView, url: String?) {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1800) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
    Glide
        .with(v.context)
        .load("${BuildConfig.MOVIE_IMAGE_ROOT_URL}$url")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(shimmerDrawable)
        .centerCrop()
        .into(v)
}

@BindingAdapter("urlFill")
fun urlFill(v: ImageView, url: String?) {
    Glide
        .with(v.context)
        .load("${BuildConfig.MOVIE_IMAGE_ROOT_URL}$url")
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(v)
}

@BindingAdapter("show")
fun show(v: View, visibility: Boolean) {
    v.visibility = if (visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("enable")
fun enable(v: View, isEnable: Boolean) {
    v.isEnabled = isEnable
}

@BindingAdapter("onPress")
fun onPress(v: View, callback: Callback?) {
    v.setOnClickListener { callback?.invoke() }
}

@BindingAdapter("app:onTextChangeWithError")
fun onTextChange(editText: TextInputEditText, callback: CallbackWithTextAndError?) {
    val str = editText.text ?: ""
    if (str.isBlank()) editText.error =
        "The field '${editText.hint.toString().lowercase()}' cannot be empty"

    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(p0: Editable?) {
            val text = p0.toString()
            val field = editText.hint.toString().lowercase()
            if (text.isBlank())
                editText.error = when (field) {
                    "password" -> "The field '$field' cannot be empty and require at least 6 characters"
                    "email" -> "The field '$field' cannot be empty"
                    else -> null
                }
            else editText.error = null
            callback?.invoke(p0.toString())
        }
    })
}

@BindingAdapter("onMovieSelected")
fun onMovieSelected(recyclerView: MovieList, callbackWithMovie: CallbackWithMovie?) {
    val adapter = recyclerView.adapter as MovieList.Adapter
    adapter.onMovieSelected = { movie ->
        callbackWithMovie?.invoke(movie)
    }
}
@BindingAdapter("onEndScroll")
fun onEndScroll(recyclerView: MovieList, callbackEndScroll: Callback?) {
    recyclerView.onEndScroll = {
        callbackEndScroll?.invoke()
    }
}