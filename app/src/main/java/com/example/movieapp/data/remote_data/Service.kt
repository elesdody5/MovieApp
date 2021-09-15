package com.example.movieapp.data.remote_data

import com.example.movieapp.data.entity.Movie
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url


interface MoviesApi {
    @GET("movies")
    fun getMovieList(): Single<List<Movie>>?

    @GET
    @Streaming
    fun downloadFile(@Url url: String): Observable<Response<ResponseBody>>
}

