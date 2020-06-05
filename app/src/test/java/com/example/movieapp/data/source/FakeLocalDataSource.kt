package com.example.movieapp.data.source

import androidx.paging.DataSource
import com.example.movieapp.data.source.local_data.LocalDataSource
import com.example.movieapp.data.source.local_data.entity.LocalMovie
import org.mockito.Mockito.mock

class FakeLocalDataSource (var movieList:MutableList<LocalMovie>):LocalDataSource{


    override fun insert(movies: List<LocalMovie>, insertFinished: () -> Unit) {
       movieList.addAll(movies)
        insertFinished()
    }

    override fun getMovies(): DataSource.Factory<Int, LocalMovie> {
        TODO("Not yet implemented")
    }


}

