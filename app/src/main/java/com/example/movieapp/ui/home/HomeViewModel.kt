package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.data.repository.MovieGetway
import javax.inject.Inject

class HomeViewModel @Inject constructor(repository: MovieGetway) : ViewModel() {

        private val movieResult = repository.getUpComingMovies()
        val movies: LiveData<PagedList<Movie>> = movieResult.data
        val networkErrors: LiveData<Boolean> = movieResult.networkErrors
        private var _openDetails = MutableLiveData<Movie>()
    val openDetails :LiveData<Movie>
            get()=_openDetails

    fun openDetails(movie: Movie) {
        _openDetails.value = movie
    }
    fun  completeNavigation(){
        _openDetails.value  =null
    }
}