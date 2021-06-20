package com.challenge.githubrepo.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.common.error.RemoteError
import com.challenge.common.state.ErrorState
import com.challenge.common.state.Loaded
import com.challenge.common.state.LoadingState
import com.challenge.common.state.State
import kotlinx.coroutines.*

abstract class BaseViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val mutableLoading = MutableLiveData<LoadingState>()

    private val mutableError = MutableLiveData<RemoteError>()

    val loading: LiveData<LoadingState> = mutableLoading
    val error: LiveData<RemoteError> = mutableError

    suspend fun <T : Any> requestData(
        apiCall: suspend () -> State<T>,
        onResult: (result: Loaded<T>) -> Unit = {},
        scope: CoroutineScope = viewModelScope
    ) {
        scope.launch(dispatcher) {
            mutableLoading.value = LoadingState.Active

            withContext(Dispatchers.IO) {
                when (val result = apiCall.invoke()) {
                    is Loaded -> {
                        mutableLoading.postValue(LoadingState.Idle)
                        onResult.invoke(result)
                    }
                    is ErrorState -> {
                        mutableLoading.postValue(LoadingState.Idle)
                        mutableError.postValue(
                            result.remoteError
                        )
                    }
                }
            }
        }
    }
}