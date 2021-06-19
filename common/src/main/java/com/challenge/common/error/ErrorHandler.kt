package com.challenge.common.error

import com.challenge.common.R
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {
    fun mapError(error: Throwable): RemoteError {
        return when {
            error.cause is ConnectException || error.cause is UnknownHostException || error is UnknownHostException -> {
                RemoteError.NoInternetConnection(R.string.no_internet_connection)
            }
            error is SocketTimeoutException -> {
                RemoteError.TimeOut(R.string.timeout)
            }
            else -> RemoteError.Unexpected(R.string.other)
        }
    }
}