package com.example.movieapp.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.entity.Movie
import com.example.movieapp.data.repository.Repository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _networkError = MutableLiveData<String>()
    val networkError: LiveData<String>
        get() = _networkError

    fun getMovies() {
        repository.getVideos()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : SingleObserver<List<Movie>> {
                override fun onSubscribe(d: Disposable) {
                    disposables.add(d)
                }

                override fun onSuccess(movies: List<Movie>) {
                    _movies.value = movies
                }

                override fun onError(e: Throwable) {
                    _networkError.value = e.message
                }
            })
    }

    fun downloadFile(movie: Movie) {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}