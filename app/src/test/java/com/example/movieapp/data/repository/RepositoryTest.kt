package com.example.movieapp.data.repository

import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.data.source.FakeLocalDataSource
import com.example.movieapp.data.source.local_data.LocalDataSource
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock

class RepositoryTest {
    var localData = mutableListOf(Movie(1), Movie(2))
    private lateinit var fakeLocalDataSource: LocalDataSource
    @Before
    fun createRepository() {
         fakeLocalDataSource = FakeLocalDataSource(localData)
    }

    @Test
    fun getUpComingMovies() {
        fakeLocalDataSource.getMovies()
        assertNotNull(fakeLocalDataSource.getMovies())
    }
}