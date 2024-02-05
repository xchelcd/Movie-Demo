package com.idaxmx.moviedemo.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Long?,
    val genres: List<MovieGenres>?,
    val language: String?,
    val title: String?,
    val overview: String?,
    @SerializedName("runtime") val _runtime: Int?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("poster_path") val posterPath: String?,
    val statusCode: Int?,
    val statusMessage: String?,
    val success: Boolean?,
) {
    val getGenres: String get() = genres?.joinToString { it.name } ?: "-"
    val duration: String get() = "$_runtime min"
}