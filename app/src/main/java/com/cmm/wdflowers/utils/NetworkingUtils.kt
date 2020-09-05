package com.cmm.wdflowers.utils

import retrofit2.Response
import java.io.IOException
import java.net.ConnectException

suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Response<T> {
    return try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
        val message = if (e is ConnectException)
            "Connection Error" else "Something went wrong. Please try again."
        throw IOException(message)
    }
}