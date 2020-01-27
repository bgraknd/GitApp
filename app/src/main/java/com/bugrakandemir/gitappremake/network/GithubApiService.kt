package com.bugrakandemir.gitappremake.network

import com.bugrakandemir.gitappremake.model.SearchMain
import com.bugrakandemir.gitappremake.model.UserInfo
import com.bugrakandemir.gitappremake.model.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiService {

    @GET("search/repositories")
    suspend fun getSearchPage(
        @Query("q") q: String?
    ): SearchMain?

    @GET("search/repositories?q=retrofit&per_page=100&page=1")
    suspend fun getSearchPage2(): SearchMain?

    @GET("users/{login}")
    suspend fun getUserInfo(@Path("login") login: String?): UserInfo?

    @GET("users/{login}/repos")
    suspend fun getUserRepos(@Path("login") login: String?): List<UserRepos>?

}