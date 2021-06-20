package com.challenge.githubrepo.fragments.pull

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.challenge.domain.model.GitPullModel
import com.challenge.domain.usecases.GetPullUseCase
import com.challenge.githubrepo.fragments.BaseViewModel
import kotlinx.coroutines.launch

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
    }
}