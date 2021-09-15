package com.example.movieapp.data.repository

import com.example.movieapp.data.entity.Movie
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response

interface Repository {
    fun getVideos(): Single<List<Movie>>?
    fun downloadFile(url: String): Observable<Response<ResponseBody>>
}