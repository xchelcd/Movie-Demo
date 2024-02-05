package com.idaxmx.moviedemo.data.model

import com.google.gson.annotations.SerializedName

data class GenericMovieResponse (
    val dates: MovieDates?,
    val page: Int?,
    val results: Set<Movie>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    val statusCode: Int?,
    val statusMessage: String?,
    val success: Boolean?,
)