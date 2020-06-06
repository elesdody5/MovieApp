package com.example.movieapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.devbyteviewer.domain.Movie
import com.example.android.devbyteviewer.domain.MovieListResult
import com.example.movieapp.data.source.local_data.LocalDataSource
import javax.inject.Inject

class Repository @Inject constructor(
     val localDataSource: LocalDataSource,
    // every new query creates a new BoundaryCallback
    // The BoundaryCallback will observe when the user reaches to the edges of
    // the list and update the database with extra data
     val boundaryCallback: MovieBoundaryCallback
) :MovieGetway{
   override fun getUpComingMovies() :MovieListResult {
        // Get data source factory from the local cache
        val dataSourceFactory = localDataSource.getMovies()
        val networkErrors = boundaryCallback.networkErrors
        // Get the paged list
        val data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return MovieListResult(data, networkErrors)
    }
    companion object {
        private const val DATABASE_PAGE_SIZE = 10
    }
}