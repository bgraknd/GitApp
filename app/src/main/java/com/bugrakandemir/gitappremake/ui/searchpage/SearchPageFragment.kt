package com.bugrakandemir.gitappremake.ui.searchpage


import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bugrakandemir.gitappremake.MainActivity
import com.bugrakandemir.gitappremake.R
import com.bugrakandemir.gitappremake.base.BaseFragment
import com.bugrakandemir.gitappremake.base.ViewModelFactory
import com.bugrakandemir.gitappremake.model.Item
import com.bugrakandemir.gitappremake.network.MainRepository
import com.bugrakandemir.gitappremake.network.RetrofitClient
import com.bugrakandemir.gitappremake.ui.searchpage.SearchPageFragmentDirections.actionSearchPageFragmentToRepoDetailsFragment
import com.bugrakandemir.gitappremake.ui.searchpage.SearchPageFragmentDirections.actionSearchPageFragmentToUserDetailsFragment
import kotlinx.android.synthetic.main.fragment_search_page.*

class SearchPageFragment : BaseFragment() {

    private val searchPageViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelFactory(mainRepository)
        ).get(SearchPageViewModel::class.java)
    }

    private val mainRepository by lazy {
        MainRepository(
            RetrofitClient.getGithubApiService()
        )
    }

    private val searchPageAdapter by lazy {
        SearchPageAdapter(
            ::onItemClick,
            ::onItemClickAvatar
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initSearch()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.setActionBarTitle(titleResId = R.string.app_name)
    }

    private fun initUi() {
        recyclerViewSearchPage.apply {
            adapter = searchPageAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initObserver(searchedText: String) {
        searchPageViewModel.initializedPagedListBuilder(searchedText)
            .observe(viewLifecycleOwner, Observer {
                imageViewGithub.visibility = View.GONE
                searchPageAdapter.submitList(it)
            })
    }

    private fun onItemClick(search: Item) {
        findNavController().navigate(
            actionSearchPageFragmentToRepoDetailsFragment(search)
        )
    }

    private fun onItemClickAvatar(search: Item) {
        findNavController().navigate(actionSearchPageFragmentToUserDetailsFragment(search.owner.login))
    }

    private fun initSearch() {
        editTextSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(v!!.text.toString())
                    return true
                }
                return false
            }
        })
    }

    private fun performSearch(searchedText: String) {

        initUi()
        initObserver(searchedText)
    }
}
