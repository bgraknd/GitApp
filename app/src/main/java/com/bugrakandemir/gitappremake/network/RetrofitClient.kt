package com.bugrakandemir.gitappremake.network

import com.bugrakandemir.gitappremake.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {

        private var githubApiService: GithubApiService? = null

        @JvmStatic
        fun getGithubApiService(): GithubApiService {
            if (githubApiService == null) {
                githubApiService = Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(GithubApiService::class.java)
            }
            return githubApiService!!
        }
    }
}