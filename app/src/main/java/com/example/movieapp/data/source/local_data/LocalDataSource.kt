package com.example.movieapp.data.source.local_data

import android.content.Context
import androidx.paging.DataSource
import com.example.android.devbyteviewer.domain.Movie
import javax.inject.Inject

interface LocalDataSource {
    fun insert(movies: List<Movie>)
    fun getMovies(): DataSource.Factory<Int, Movie>
}

class MovieLocalDataSource @Inject constructor( val context: Context) : LocalDataSource {
    private val dao: MovieDao

    init {
        val dataBase = MovieDatabase.getInstance(context.applicationContext)
        dao = dataBase.movieDao
    }

    override fun insert(movies: List<Movie>) {
        dao.insert(movies)
    }

    override fun getMovies(): DataSource.Factory<Int, Movie> {
        return dao.getMovies()
    }

}