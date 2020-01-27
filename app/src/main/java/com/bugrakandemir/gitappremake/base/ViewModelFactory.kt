package com.bugrakandemir.gitappremake.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bugrakandemir.gitappremake.network.MainRepository
import com.bugrakandemir.gitappremake.ui.repodetails.RepoDetailsViewModel
import com.bugrakandemir.gitappremake.ui.searchpage.SearchPageViewModel
import com.bugrakandemir.gitappremake.ui.userinfo.UserDetailsViewModel

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchPageViewModel::class.java) -> SearchPageViewModel(
                repository as MainRepository
            ) as T
            modelClass.isAssignableFrom(RepoDetailsViewModel::class.java) -> RepoDetailsViewModel(
                repository as MainRepository
            ) as T
            modelClass.isAssignableFrom(UserDetailsViewModel::class.java) -> UserDetailsViewModel(
                repository as MainRepository
            ) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}