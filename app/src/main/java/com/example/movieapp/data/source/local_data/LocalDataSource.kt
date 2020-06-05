package com.example.movieapp.data.source.local_data

import android.content.Context
import android.content.IntentSender
import androidx.paging.DataSource
import com.example.movieapp.data.source.local_data.entity.LocalMovie

interface LocalDataSource {
    fun insert(movies: List<LocalMovie>, insertFinished: () -> Unit)
    fun getMovies(): DataSource.Factory<Int, LocalMovie>
}

class MovieLocalDataSource(private val context: Context) : LocalDataSource {
    private val dao: MovieDao

    init {
        val dataBase = MovieDatabase.getInstance(context.applicationContext)
        dao = dataBase.movieDao
    }

    override fun insert(movies: List<LocalMovie>, insertFinished: () -> Unit) {
        dao.insert(movies)
        insertFinished()
    }

    override fun getMovies(): DataSource.Factory<Int, LocalMovie> {
        return dao.getMovies()
    }

}