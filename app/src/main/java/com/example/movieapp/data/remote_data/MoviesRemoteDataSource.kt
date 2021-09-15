package com.example.movieapp.data.remote_data

import com.example.movieapp.data.entity.Movie
import io.reactivex.Single
import javax.inject.Inject


class MoviesRemoteDataSource @Inject constructor(private val api: MoviesApi) : RemoteDataSource {

    override fun getVideos(): Single<List<Movie>>? {
        return api.getMovieList()
    }


}