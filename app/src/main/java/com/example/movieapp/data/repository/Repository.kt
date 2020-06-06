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
     val data:LiveData<PagedList<Movie>>,
    // every new query creates a new BoundaryCallback
    // The BoundaryCallback will observe when the user reaches to the edges of
    // the list and update the database with extra data
     val boundaryCallback: MovieBoundaryCallback
) :MovieGetway{
   override fun getUpComingMovies() :MovieListResult {

        val networkErrors = boundaryCallback.networkErrors
        return MovieListResult(data, networkErrors)
    }

}