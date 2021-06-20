package com.challenge.githubrepo.fragments.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.challenge.domain.model.GitRepoModel
import com.challenge.domain.usecases.GetRepoUseCase
import com.challenge.githubrepo.fragments.BaseViewModel
import kotlinx.coroutines.launch

class GitRepoViewModel(private val getRepoUseCase: GetRepoUseCase) : BaseViewModel() {

    private val mutableLoaded = MutableLiveData<List<GitRepoModel>>()

    val loaded: LiveData<List<GitRepoModel>> = mutableLoaded

    private var currentPage: Int = 1

    init {
        loadRepoByPage()
    }

    fun loadRepoByPage() {
        viewModelScope.launch {
            requestData(
                { getRepoUseCase(currentPage) },
                { result ->
                    if (result.result.isNotEmpty()) {
                        currentPage++
                    }
                    mutableLoaded.postValue(
                        result.result
                    )
                }
            )
        }
    }
}