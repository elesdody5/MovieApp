package com.example.movieapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.movieapp.data.repository.MovieGetway
import com.example.movieapp.data.source.local_data.entity.LocalMovie

class HomeViewModel constructor(private val repository: MovieGetway):ViewModel() {

    private val movieResult= repository.getUpComingMovies()
    val movies: LiveData<PagedList<LocalMovie>> = movieResult.data
    val networkErrors: LiveData<String> = movieResult.networkErrors

}