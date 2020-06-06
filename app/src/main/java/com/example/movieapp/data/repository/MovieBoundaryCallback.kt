package com.example.movieapp.data.repository

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.data.source.local_data.LocalDataSource
import com.example.movieapp.data.source.remote_data.NetworkMovieContainer
import com.example.movieapp.data.source.remote_data.RemoteDataSource
import com.example.movieapp.data.source.remote_data.asDatabaseModel
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data.
 **/
class MovieBoundaryCallback @Inject constructor(
     val service: RemoteDataSource,
     val cache: LocalDataSource,
     private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PagedList.BoundaryCallback<Movie>() {


    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<Boolean>()

    // LiveData of network errors.
    val networkErrors: LiveData<Boolean>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: Movie) {
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return
        isRequestInProgress = true
         CoroutineScope(ioDispatcher).launch {
             try {
                 val movieList =  service.getMovieList(lastRequestedPage)
                 cache.insert(movieList.asDatabaseModel())
                 lastRequestedPage++
                 isRequestInProgress = false
             }catch (e:Exception){
                 Log.d(ContentValues.TAG, "error ")
                 _networkErrors.postValue(true)
                 isRequestInProgress = false
             }

         }
    }
}