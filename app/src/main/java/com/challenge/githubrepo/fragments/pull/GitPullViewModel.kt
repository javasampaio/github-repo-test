package com.challenge.githubrepo.fragments.pull

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.common.error.RemoteError
import com.challenge.common.state.ErrorState
import com.challenge.common.state.Loaded
import com.challenge.common.state.LoadingState
import com.challenge.domain.model.GitPullModel
import com.challenge.domain.model.GitRepoModel
import com.challenge.domain.usecases.GetPullUseCase
import com.challenge.githubrepo.fragments.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GitPullViewModel(
    private val author: String,
    private val repoName: String,
    private val getPullUseCase: GetPullUseCase) : BaseViewModel() {

    private val mutableLoaded = MutableLiveData<List<GitPullModel>>()

    val loaded: LiveData<List<GitPullModel>> = mutableLoaded

    init {
        loadPulls()
    }

    private fun loadPulls() {
        viewModelScope.launch {
            requestData(
                { getPullUseCase(author, repoName) },
                { result ->
                    mutableLoaded.postValue(
                        result.result
                    )
                }
            )
        }

        /*viewModelScope.launch {
            mutableLoading.value = LoadingState.Active

            withContext(Dispatchers.IO) {
                when (val repoResult = getPullUseCase(author, repoName)) {
                    is Loaded -> {
                        mutableLoading.postValue(LoadingState.Idle)


                        mutableLoaded.postValue(
                            repoResult.result
                        )
                    }
                    is ErrorState -> {
                        mutableLoading.postValue(LoadingState.Idle)
                        mutableError.postValue(
                            repoResult.remoteError
                        )
                    }
                }
            }
        }*/
    }
}