package com.example.movieapp.data.local_data

import android.content.Context
import androidx.paging.DataSource
import com.example.movieapp.data.Result
import com.example.movieapp.data.Result.*
import com.example.movieapp.data.local_data.entity.LocalMovie

interface LocalDataSource {
    fun insert(movies: List<LocalMovie>)
    fun getMovies(): Result<DataSource.Factory<Int, LocalMovie>>
}

class MovieLocalDataSource(val context:Context) : LocalDataSource {
    private val dao: MovieDao
    init {
        val dataBase = MovieDatabase.getInstance(context.applicationContext)
        dao = dataBase.movieDao
    }
     override fun insert(movies: List<LocalMovie>) {
        dao.insert(movies)
    }

     override fun getMovies(): Result<DataSource.Factory<Int, LocalMovie>> {
        return try {
            Success(dao.getMovies())
        } catch (e: Exception) {
            Error(e)
        }
    }

}