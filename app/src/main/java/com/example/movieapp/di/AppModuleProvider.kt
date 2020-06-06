package com.example.movieapp.di

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.data.repository.MovieBoundaryCallback
import com.example.movieapp.data.source.local_data.LocalDataSource
import com.example.movieapp.data.source.local_data.MovieLocalDataSource
import com.example.movieapp.data.source.remote_data.MovieRemoteSource
import com.example.movieapp.data.source.remote_data.RemoteDataSource
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class AppModuleProvider {
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return MovieRemoteSource()
    }
    @Provides
    fun provideLocalDataSource(context: Context): LocalDataSource {
        return MovieLocalDataSource(context)
    }
    @Provides
    fun providePagingList(localDataSource: LocalDataSource,boundaryCallback:MovieBoundaryCallback):LiveData<PagedList<Movie>>{
        return LivePagedListBuilder(localDataSource.getMovies(), DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
    }
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO
    companion object {
        private const val DATABASE_PAGE_SIZE = 10
    }
}