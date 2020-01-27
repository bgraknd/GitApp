package com.bugrakandemir.gitappremake.ui.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bugrakandemir.gitappremake.base.BaseViewModel
import com.bugrakandemir.gitappremake.model.UserInfo
import com.bugrakandemir.gitappremake.model.UserRepos
import com.bugrakandemir.gitappremake.network.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UserDetailsViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    private var getUserReposJob: Job? = null
    private var getUserInfoJob: Job? = null
    val getUserReposResult = MutableLiveData<List<UserRepos>>()
    val getUserInfoResult = MutableLiveData<UserInfo>()

    fun getUserRepos(login: String) {
        if (getUserReposJob?.isActive == true) {
            return
        }
        getUserReposJob = launchGetUserRepos(login)
    }

    fun getUserInfo(login: String) {
        if (getUserInfoJob?.isActive == true) {
            return
        }
        getUserInfoJob = launchGetUserInfo(login)
    }

    private fun launchGetUserRepos(login: String): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            val result = mainRepository.getUserRepos(login)
            getUserReposResult.postValue(result)
        }
    }

    private fun launchGetUserInfo(login: String): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            val resultInfo = mainRepository.getUserInfo(login)
            getUserInfoResult.postValue(resultInfo)
        }
    }
}