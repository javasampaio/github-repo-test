package com.challenge.common.error

import org.junit.Test
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorTest {


    @Test
    fun shouldThrowNoInternetConnection() {

        assert(
            ErrorHandler.mapError(
                Throwable(cause = ConnectException())
            ) is RemoteError.NoInternetConnection
        )

        assert(
            ErrorHandler.mapError(
                Throwable(cause = UnknownHostException())
            ) is RemoteError.NoInternetConnection
        )

        assert(
            ErrorHandler.mapError(UnknownHostException()) is RemoteError.NoInternetConnection
        )
    }

    @Test
    fun shouldThrowUnexpected() {

        assert(
            ErrorHandler.mapError(
                Throwable(cause = Exception())
            ) is RemoteError.Unexpected
        )
    }

    @Test
    fun shouldThrowSocketTimeoutException() {

        assert(
            ErrorHandler.mapError(SocketTimeoutException()) is RemoteError.TimeOut
        )
    }
}