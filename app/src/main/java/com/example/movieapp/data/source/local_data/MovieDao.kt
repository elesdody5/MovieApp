package com.example.movieapp.data.source.local_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.paging.DataSource
import com.example.android.devbyteviewer.domain.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>)


    @Query("SELECT * FROM Movie")
    fun getMovies(): DataSource.Factory<Int, Movie>
}