package com.example.faridaziz.kopinema.repo

import android.util.Log
import okhttp3.*

/**
 * @class [Service]
 * Merupakan class untuk membuat sebuah
 * Service (Network Request) Builder.
 */

class Service {
    private val urlBuilder = HttpUrl.Builder()

    fun sending(queryParams: Map<String, String>): Call {
        urlBuilder.scheme("http")
        urlBuilder.host("192.168.1.4")
        urlBuilder.port(5000)

        queryParams.forEach { (key, value) ->
            urlBuilder.removeAllQueryParameters(key)
            urlBuilder.addQueryParameter(key, value)
        }

        val url = urlBuilder.build()

        Log.i(this.javaClass.simpleName, "URL : ${url}")
        return OkHttpClient().newCall(
                Request.Builder()
                        .url(url)
                        .build()
        )
    }
}