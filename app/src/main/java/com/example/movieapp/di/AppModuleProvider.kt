package com.example.movieapp.di

import android.content.Context
import com.example.movieapp.data.source.local_data.LocalDataSource
import com.example.movieapp.data.source.local_data.MovieLocalDataSource
import com.example.movieapp.data.source.remote_data.MovieRemoteSource
import com.example.movieapp.data.source.remote_data.RemoteDataSource
import dagger.Module
import dagger.Provides

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
}