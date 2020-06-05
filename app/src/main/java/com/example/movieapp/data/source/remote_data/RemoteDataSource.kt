package com.example.movieapp.data.source.remote_data

import retrofit2.Call

interface RemoteDataSource {
     fun getMovieList(page: Int): Call<NetworkMovieContainer>
}

class MovieRemoteSource : RemoteDataSource {

    override  fun getMovieList(page:Int): Call<NetworkMovieContainer> {
        return Network.movieService.getMovieList(page)
    }
}