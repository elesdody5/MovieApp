package com.example.movieapp.data.repository

import com.example.movieapp.data.entity.Movie
import io.reactivex.Single

interface Repository {
    fun getVideos(): Single<List<Movie>>?

}