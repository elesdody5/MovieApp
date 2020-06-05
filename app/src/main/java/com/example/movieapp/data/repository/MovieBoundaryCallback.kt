package com.example.movieapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.example.android.devbyteviewer.domain.Movie
import com.example.movieapp.data.source.local_data.LocalDataSource
import com.example.movieapp.data.source.local_data.entity.LocalMovie
import com.example.movieapp.data.source.remote_data.NetworkMovieContainer
import com.example.movieapp.data.source.remote_data.RemoteDataSource
import com.example.movieapp.data.source.remote_data.asDatabaseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * This boundary callback gets notified when user reaches to the edges of the list for example when
 * the database cannot provide any more data.
 **/
class MovieBoundaryCallback(
    private val service: RemoteDataSource,
    private val cache: LocalDataSource
) : PagedList.BoundaryCallback<LocalMovie>() {


    // keep the last requested page. When the request is successful, increment the page number.
    private var lastRequestedPage = 1

    private val _networkErrors = MutableLiveData<String>()

    // LiveData of network errors.
    val networkErrors: LiveData<String>
        get() = _networkErrors

    // avoid triggering multiple requests in the same time
    private var isRequestInProgress = false

    /**
     * Database returned 0 items. We should query the backend for more items.
     */
    override fun onZeroItemsLoaded() {
        Log.d("RepoBoundaryCallback", "onZeroItemsLoaded")
        requestAndSaveData()
    }

    /**
     * When all items in the database were loaded, we need to query the backend for more items.
     */
    override fun onItemAtEndLoaded(itemAtEnd: LocalMovie) {
        Log.d("BoundaryCallback", "onItemAtEndLoaded")
        requestAndSaveData()
    }

    private fun requestAndSaveData() {
        if (isRequestInProgress) return

        isRequestInProgress = true

        service.getMovieList(lastRequestedPage).enqueue(object :
            retrofit2.Callback<NetworkMovieContainer> {
            override fun onFailure(call: Call<NetworkMovieContainer>, t: Throwable) {
                _networkErrors.postValue(t.message)
                isRequestInProgress = false
            }

            override fun onResponse(
                call: Call<NetworkMovieContainer>,
                response: Response<NetworkMovieContainer>
            ) {
                response.body()?.let {
                    val localMovie = it.asDatabaseModel()
                    cache.insert(localMovie) {
                        lastRequestedPage++
                        isRequestInProgress = false
                    }
                }
            }
        })

    }
}