package com.lightone.lighthouse.kotlin.util

import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.AsyncTask
import android.view.View
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnErrorListener
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.ybq.android.spinkit.SpinKitView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class loadpdffromUrl(val pdfView: PDFView, val progress: SpinKitView) : AsyncTask<String?, Void?, InputStream?>(), OnLoadCompleteListener, OnErrorListener {
    override fun doInBackground(vararg params: String?): InputStream? {
        //We use InputStream to get PDF.
        var inputStream: InputStream? = null
        try {
            val url = URL(strings[0])
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            if (urlConnection.responseCode == 200) {
                // if response is success. we are getting input stream from url and storing it in our variable.
                inputStream = BufferedInputStream(urlConnection.inputStream)
            }
        } catch (e: IOException) {
            //method to handle errors.
            e.printStackTrace()
            return null
        }
        return inputStream
    }

    override fun onPostExecute(inputStream: InputStream?) {
        //after the executing async task we load pdf in to pdfview.
        pdfView.fromStream(inputStream).onLoad(this).onError(this).load()
    }

    override fun loadComplete(nbPages: Int) {
        progress.visibility = View.GONE
    }

    override fun onError(t: Throwable) {
        progress.visibility = View.GONE
    }
}

//// backgroundTask를 실행하는 메소드
//private fun sampleMethod() {
//    // task에서 반환할 Hashmap
//    val map: HashMap<String?, String?> = HashMap()
//
//    //onPreExecute(task 시작 전 실행될 코드 여기에 작성)
//    backgroundTask = Observable.fromCallable { map }.subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(object : Consumer<HashMap<String?, String?>?>() {
//            fun accept(map: HashMap<String?, String?>?) {
//                //onPostExecute(task 끝난 후 실행될 코드 여기에 작성)
//                backgroundTask.dispose()
//            }
//        })
//}