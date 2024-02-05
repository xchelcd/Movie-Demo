package com.idaxmx.moviedemo.util.binding

import com.idaxmx.moviedemo.data.model.Movie

fun interface Callback {
    operator fun invoke()
}

fun interface CallbackWithText {
    operator fun invoke(s: String)
}

fun interface CallbackWithTextAndError {
    operator fun invoke(s: String)
}

fun interface CallbackWithMovie {
    operator fun invoke(movie: Movie)
}