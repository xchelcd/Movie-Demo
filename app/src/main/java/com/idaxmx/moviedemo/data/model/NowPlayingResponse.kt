package com.idaxmx.moviedemo.data.model

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse (
    val dates: MovieDates?,
    val page: Int?,
    val results: List<Movie>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?,
    val statusCode: Int?,
    val statusMessage: String?,
    val success: Boolean?,
)

open class GenericResponse (
    open val statusCode: Int?,
    open val statusMessage: String?,
    open val success: Boolean?,
)