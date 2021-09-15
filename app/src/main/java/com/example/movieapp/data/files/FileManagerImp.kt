package com.example.movieapp.data.files

import android.os.Environment
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

import okhttp3.ResponseBody
import java.io.*
import kotlin.math.pow
import kotlin.math.roundToInt


class FileManagerImp : DownLoadFileManager {
    private lateinit var emitter: ObservableEmitter<Int>

    override val downloadingFileProgress = Observable.create(
        ObservableOnSubscribe<Int> {
            emitter = it
        }
    )

    @Throws(IOException::class)
    override fun downloadFile(name: String, body: ResponseBody) {
        var count: Int
        val data = ByteArray(1024 * 4)
        val fileSize = body.contentLength()
        val bis: InputStream = BufferedInputStream(body.byteStream(), 1024 * 8)
        val outputFile = File(
            name
        )
        val output: OutputStream = FileOutputStream(outputFile)
        var total: Long = 0
        val startTime = System.currentTimeMillis()
        var timeCount = 1
        while (bis.read(data).also { count = it } != -1) {
            total += count.toLong()
//            totalFileSize = (fileSize / 1024.0.pow(2.0)).toInt()
            val current = (total / 1024.0.pow(2.0)).roundToInt().toDouble()
            val progress = (total * 100 / fileSize).toInt()
//            val currentTime = System.currentTimeMillis() - startTime
//            val download = Download()
//            download.setTotalFileSize(totalFileSize)

//            if (currentTime > 1000 * timeCount) {
//                download.setCurrentFileSize(current.toInt())
//                download.setProgress(progress)
//                sendNotification(download)
//                timeCount++
//            }
            output.write(data, 0, count)
            emitter.onNext(progress)
        }
        emitter.onComplete()
        output.flush()
        output.close()
        bis.close()
    }

}