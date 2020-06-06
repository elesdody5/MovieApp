package com.example.movieapp.data.source.remote_data

import com.example.movieapp.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Movie API communication setup via Retrofit.
 */
private const val TMDB_BASE_URL = "https://api.themoviedb.org/3/movie/"
const val TMDB_IMAGEURL = "https://image.tmdb.org/t/p/w500/"
interface MovieApi {
    @GET("upcoming?api_key=${BuildConfig.API_KEY}&language=en-US")
   suspend fun getMovieList(@Query("page") page: Int):NetworkMovieContainer
}

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
/**
 * Main entry point for network access. Call like `Network.devbytes.getPlaylist()`
 */
object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(TMDB_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val movieService = retrofit.create(MovieApi::class.java)
}