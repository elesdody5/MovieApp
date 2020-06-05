package com.example.movieapp.data.source.remote_data

import com.example.movieapp.data.source.local_data.entity.LocalMovie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * DataTransferObjects go in this file. These are responsible for parsing responses from the server
 * or formatting objects to send to the server. You should convert these to domain objects before
 * using them.
 */
@JsonClass(generateAdapter = true)
data class NetworkMovieContainer(val results:List<NetworkMovie>)

data class NetworkMovie(
    val id: Int,
    @Json(name = "original_title")
    val title: String="",
    @Json(name = "release_date")
    val releaseDate: String="",
    @Json(name = "poster_path")
    val posterPath: String="",
    @Json(name = "vote_average")
    val rating: Double,
    val overview:String
)
/**
 * Convert Network results to database objects
 */
fun NetworkMovieContainer.asDatabaseModel(): List<LocalMovie> {
    return results.map {
        LocalMovie(
            title = it.title,
            overview = it.overview,
            posterPath = TMDB_IMAGEURL+it.posterPath,
            rating = it.rating,
            releaseDate = it.releaseDate)
    }
}
