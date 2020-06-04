package com.example.movieapp.data.local_data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalMovie(
    @PrimaryKey
    val id: Int=-1,
    val title: String="",
    val releaseDate: String="",
    val posterPath: String="",
    val rating: Double=0.0
)