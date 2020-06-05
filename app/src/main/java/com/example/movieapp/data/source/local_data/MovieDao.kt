package com.example.movieapp.data.source.local_data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.paging.DataSource
import com.example.movieapp.data.source.local_data.entity.LocalMovie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<LocalMovie>)


    @Query("SELECT * FROM LocalMovie")
    fun getMovies(): DataSource.Factory<Int, LocalMovie>
}