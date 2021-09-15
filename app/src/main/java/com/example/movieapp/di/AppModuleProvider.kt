package com.example.movieapp.di

import com.example.movieapp.data.remote_data.*
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val BASE_URL = "https://nagwa.free.beeceptor.com/"

@Module
class AppModuleProvider {
    @Provides
    fun provideRemoteDataSource(api: MoviesApi): RemoteDataSource {
        return MoviesRemoteDataSource(api)
    }

    @Provides
    @Singleton
    fun provideMovieApi(): MoviesApi {
        // Configure retrofit to parse JSON and use coroutines
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(MoviesApi::class.java)
    }
}