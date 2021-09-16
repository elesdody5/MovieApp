package com.example.movieapp.ui.download

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import java.io.BufferedInputStream
import java.io.File
import java.io.InputStream
import javax.inject.Inject


class DownloadViewModel @Inject constructor(
    private val repository: Repository,
    private val application: Application
) : ViewModel() {

    private val _progress = MutableLiveData<Int>()

    val progress: LiveData<Int>
        get() = _progress

    private val _complete = MutableLiveData<Boolean>()

    val complete: LiveData<Boolean>
        get() = _complete

    private val _networkError = MutableLiveData<String>()
    val networkError: LiveData<String>
        get() = _networkError

    fun downloadFile(movie: Movie) {
        movie.url?.let { it ->
            repository.downloadFile(it)
                .flatMap(object : Function1<Response<ResponseBody>, Observable<File>> {
                    override fun invoke(responseBodyResponse: Response<ResponseBody>): Observable<File> {
                        return Observable.create { emitter ->
                            val file = writeFile(responseBodyResponse.body(), movie.name)
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
                        _complete.value = true
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.d("downloadZipFile", "Error " + e.message)
                        _networkError.value = e.message
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

    private fun writeFile(body: ResponseBody?, movieName: String?): File {
        val fileSize = body?.contentLength()

        val bis: InputStream = BufferedInputStream(
            body?.byteStream()
        )
        val data = ByteArray(1024 * 4)
        var count: Int = 0
        var total: Long = 0

        val file = File(movieName)

        val fileOutputStream =
            application.openFileOutput(file.name, Context.MODE_PRIVATE)

        while (bis.read(data).also { count = it } !== -1) {
            total += count
            val progress = (total * 100 / fileSize!!).toInt()
            _progress.value = progress
            Log.i("download view model", "progress$progress")
            fileOutputStream.write(data, 0, count)
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        bis.close()

        return file
    }
}