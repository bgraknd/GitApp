package com.bugrakandemir.gitappremake.ui.searchpage

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.bugrakandemir.gitappremake.base.BaseViewModel
import com.bugrakandemir.gitappremake.model.Item
import com.bugrakandemir.gitappremake.network.PostsDataSource
import kotlinx.coroutines.Dispatchers

class SearchPageViewModel : BaseViewModel() {

    private val config = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(false)
        .build()

    fun initializedPagedListBuilder(text: String): LiveData<PagedList<Item>> {

        val dataSourceFactory = object : DataSource.Factory<Int, Item>() {
            override fun create(): DataSource<Int, Item> {
                return PostsDataSource(Dispatchers.IO, text)
            }
        }
        return LivePagedListBuilder<Int, Item>(dataSourceFactory, config).build()
    }
}