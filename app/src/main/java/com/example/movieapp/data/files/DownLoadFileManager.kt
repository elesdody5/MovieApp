package com.example.movieapp.data.files

import io.reactivex.Observable
import okhttp3.ResponseBody

interface DownLoadFileManager {
    val downloadingFileProgress: Observable<Int>
    fun downloadFile(name: String, body: ResponseBody)
}
