package com.example.movieapp.data.remote_data

import com.example.movieapp.data.entity.Movie
import io.reactivex.Single
import retrofit2.http.GET


interface MoviesApi {
    @GET("movies")
    fun getMovieList(): Single<List<Movie>>?
}

