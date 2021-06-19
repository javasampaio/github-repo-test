package com.challenge.common.error

import androidx.annotation.StringRes

sealed class RemoteError(@StringRes var message: Int) {
    class NoInternetConnection(message: Int) : RemoteError(message)
    class TimeOut(message: Int) : RemoteError(message)
    class Unexpected(message: Int) : RemoteError(message)
}