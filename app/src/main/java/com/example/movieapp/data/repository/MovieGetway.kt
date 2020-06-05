package com.example.movieapp.data.repository

import com.example.android.devbyteviewer.domain.MovieListResult

interface MovieGetway {
    fun getUpComingMovies() : MovieListResult
}