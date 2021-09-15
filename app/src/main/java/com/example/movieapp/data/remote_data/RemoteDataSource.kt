package com.example.movieapp.data.remote_data

import com.example.movieapp.data.entity.Movie
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response

interface RemoteDataSource {
    fun getVideos(): Single<List<Movie>>?
    fun downloadVideo(url:String):Observable<Response<ResponseBody>>
}