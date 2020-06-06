package com.example.movieapp.data.source

import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.data.source.remote_data.NetworkMovie
import com.example.movieapp.data.source.remote_data.NetworkMovieContainer
import com.example.movieapp.data.source.remote_data.RemoteDataSource

class FakeRemoteDataSource( var movieList: List<NetworkMovie>?):RemoteDataSource {
    override suspend fun getMovieList(page: Int): NetworkMovieContainer {
        return NetworkMovieContainer(movieList!!)
    }

}