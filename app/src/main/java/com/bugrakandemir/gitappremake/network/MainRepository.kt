package com.bugrakandemir.gitappremake.network

import com.bugrakandemir.gitappremake.base.BaseRepository
import com.bugrakandemir.gitappremake.model.SearchMain
import com.bugrakandemir.gitappremake.model.UserInfo
import com.bugrakandemir.gitappremake.model.UserRepos

class MainRepository(private val githubApiService: GithubApiService) : BaseRepository() {

    suspend fun getSearchPage(q: String): SearchMain? {
        return githubApiService.getSearchPage(q)
    }

    suspend fun getSearchPage2(): SearchMain? {
        return githubApiService.getSearchPage2()
    }

    suspend fun getUserInfo(login: String): UserInfo? {
        return githubApiService.getUserInfo(login)
    }

    suspend fun getUserRepos(login: String): List<UserRepos> {
        return githubApiService.getUserRepos(login).orEmpty()
    }
}