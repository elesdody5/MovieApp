package com.example.movieapp.data.source.remote_data

import retrofit2.Call

interface RemoteDataSource {
    suspend fun getMovieList(page: Int): NetworkMovieContainer
}

class MovieRemoteSource : RemoteDataSource {

    override suspend fun getMovieList(page:Int): NetworkMovieContainer {
        return Network.movieService.getMovieList(page)
    }
}