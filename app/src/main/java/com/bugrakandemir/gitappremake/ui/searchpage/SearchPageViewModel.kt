package com.bugrakandemir.gitappremake.ui.searchpage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bugrakandemir.gitappremake.base.BaseViewModel
import com.bugrakandemir.gitappremake.model.SearchMain
import com.bugrakandemir.gitappremake.network.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchPageViewModel(private val mainRepository: MainRepository) : BaseViewModel() {

    private var getSearchPageJob: Job? = null
    val getSearchPageResult = MutableLiveData<SearchMain>()

    fun getSearchPage(q: String) {
        if (getSearchPageJob?.isActive == true) {
            return
        }
        getSearchPageJob = launchGetSearchPage(q)
    }

    private fun launchGetSearchPage(q: String): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            val result = mainRepository.getSearchPage(q)
            getSearchPageResult.postValue(result)
        }
    }
}