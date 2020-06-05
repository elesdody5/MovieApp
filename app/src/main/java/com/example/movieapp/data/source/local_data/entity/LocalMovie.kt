package com.example.movieapp.data.source.local_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.devbyteviewer.domain.Movie

@Entity
data class LocalMovie(
    @PrimaryKey
    val id: Int = -1,
    val title: String = "",
    val releaseDate: String = "",
    val posterPath: String = "",
    val rating: Double = 0.0,
    val overview: String = ""
)

fun List<LocalMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            title = it.title,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            rating = it.rating
        )
    }
}