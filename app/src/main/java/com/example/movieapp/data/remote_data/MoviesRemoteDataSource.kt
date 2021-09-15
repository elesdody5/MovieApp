package com.example.movieapp.data.remote_data

import com.example.movieapp.data.entity.Movie
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject


class MoviesRemoteDataSource @Inject constructor(private val api: MoviesApi) : RemoteDataSource {

    override fun getVideos(): Single<List<Movie>>? {
        return api.getMovieList()
    }

    override fun downloadVideo(url: String): Observable<Response<ResponseBody>> {
        return api.downloadFile(url)
    }


}