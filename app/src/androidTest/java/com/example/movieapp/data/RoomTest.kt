package com.example.movieapp.data

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.movieapp.data.source.local_data.MovieDao
import com.example.movieapp.data.source.local_data.MovieDatabase
import com.example.movieapp.data.source.local_data.entity.LocalMovie
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RoomTest {
    private lateinit var movieDao: MovieDao
    private lateinit var db: MovieDatabase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        movieDao = db.movieDao
    }
    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetList() {
        val movieList = listOf(LocalMovie(id = 1), LocalMovie(id = 2))

        movieDao.insert(movieList)
        val factory = movieDao.getMovies()
        val list = (factory.create() as LimitOffsetDataSource).loadRange(0, 2)
        Assert.assertEquals(list[0].id, 1)
    }
}