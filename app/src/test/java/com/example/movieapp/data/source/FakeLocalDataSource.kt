package com.example.movieapp.data.source

import androidx.paging.DataSource
import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.data.source.local_data.LocalDataSource
import org.mockito.Mock
import org.mockito.Mockito.mock

class FakeLocalDataSource (var movieList:MutableList<Movie>):LocalDataSource{

    override fun insert(movies: List<Movie>, insertFinished: () -> Unit) {
       movieList.addAll(movies)
        insertFinished()
    }

    override fun getMovies(): DataSource.Factory<Int, Movie> {
      val dataFactory: DataSource.Factory<Int,Movie>  = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movie>
        return dataFactory
    }


}

