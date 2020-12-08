package com.razbakov.sandbox.network

import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber

class Pinger(
    private val okHttpClient: OkHttpClient
) {

    fun ping(url: String): Boolean {
        try {
            okHttpClient
                .newCall(Request.Builder().get().url("http://$url").build())
                .execute()
                .let {
                    Timber.d("Ping $url with response ${it.code}")
                    return it.isSuccessful
                }
        } catch (e: Exception) {
            Timber.e(e)
        }
        return false
    }
}
