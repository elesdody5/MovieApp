package com.example.movieapp.data.repository

import com.example.movieapp.data.entity.Movie
import com.example.movieapp.data.remote_data.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override fun getVideos(): Single<List<Movie>>? {
        return remoteDataSource.getVideos()
    }

    override fun downloadFile(url: String): Observable<Response<ResponseBody>> {
        return remoteDataSource.downloadVideo(url)
    }


}


