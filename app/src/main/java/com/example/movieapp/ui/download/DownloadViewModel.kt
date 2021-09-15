package com.example.movieapp.ui.download

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.movieapp.data.entity.Movie
import com.example.movieapp.data.repository.Repository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import javax.inject.Inject

class DownloadViewModel @Inject constructor(
    private val repository: Repository,
    private val application: Application
) : ViewModel() {

    fun downloadFile(movie: Movie) {
        movie.url?.let { it ->
            repository.downloadFile(it)
                .flatMap(object : Function1<Response<ResponseBody>, Observable<File>> {
                    override fun invoke(responseBodyResponse: Response<ResponseBody>): Observable<File> {
                        return Observable.create { emitter ->
                            val file = File(movie.name ?: "")

                            val fileOutputStream =
                                application.openFileOutput(file.name, Context.MODE_PRIVATE)


                            fileOutputStream.write(
                                responseBodyResponse.body()?.source().toString().toByteArray()
                            )
                            fileOutputStream.close()
                            emitter.onNext(file)
                            emitter.onComplete()

                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<File?> {
                    override fun onComplete() {
                        Log.d("downloadZipFile", "onCompleted")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.d("downloadZipFile", "Error " + e.message)
                    }

                    override fun onNext(file: File) {
                        Log.d("downloadZipFile", "File downloaded to " + file.getAbsolutePath())
                    }

                    override fun onSubscribe(d: Disposable) {
                        Log.d("downloadZipFile", "onSubscribe: ")
                    }
                })
        }
    }
}