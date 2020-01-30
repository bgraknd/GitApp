package com.bugrakandemir.gitappremake.network

import androidx.paging.PageKeyedDataSource
import com.bugrakandemir.gitappremake.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val PAGE_SIZE: Int = 10
private const val FIRST_PAGE: Int = 1

class PostsDataSource(coroutineContext: CoroutineContext, private val searchedText: String) :
    PageKeyedDataSource<Int, Item>() {
    private val retrofitService = RetrofitClient.getGithubApiService()

    private val job = Job()
    private val scope = CoroutineScope(coroutineContext + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        scope.launch {
            val response = retrofitService.getSearchPage(searchedText, PAGE_SIZE, FIRST_PAGE)
            if (response.isSuccessful) {
                val listing = response.body()!!.items
                callback.onResult(listing, null, FIRST_PAGE + 1)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        scope.launch {
            val response = retrofitService.getSearchPage(searchedText, PAGE_SIZE, params.key)
            if (response.isSuccessful) {
                val listing = response.body()!!.items
                callback.onResult(listing, params.key + 1)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Item>
    ) {
    }

    override fun invalidate() {
        super.invalidate()
        job.cancel()
    }
}