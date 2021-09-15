package com.example.movieapp.data.remote_data

import com.example.movieapp.data.entity.Movie
import io.reactivex.Single

interface RemoteDataSource {
    fun getVideos(): Single<List<Movie>>?
}