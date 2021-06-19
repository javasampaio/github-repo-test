package com.challenge.common.state

import com.challenge.common.error.RemoteError

sealed class State<out T : Any>
class Loaded<out T : Any>(val result: T) : State<T>()
class ErrorState(val remoteError: RemoteError?) : State<Nothing>()

sealed class LoadingState {
    object Idle : LoadingState()
    object Active : LoadingState()
}