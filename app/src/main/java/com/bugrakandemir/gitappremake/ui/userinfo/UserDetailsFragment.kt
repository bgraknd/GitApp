package com.bugrakandemir.gitappremake.ui.userinfo


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bugrakandemir.gitappremake.MainActivity
import com.bugrakandemir.gitappremake.R
import com.bugrakandemir.gitappremake.base.BaseFragment
import com.bugrakandemir.gitappremake.base.ViewModelFactory
import com.bugrakandemir.gitappremake.network.MainRepository
import com.bugrakandemir.gitappremake.network.RetrofitClient
import kotlinx.android.synthetic.main.fragment_user_details.*

/**
 * A simple [Fragment] subclass.
 */
class UserDetailsFragment : BaseFragment() {
    private val responseList = ArrayList<BaseInfo>()
    private val mainRepository by lazy {
        MainRepository(
            RetrofitClient.getGithubApiService()
        )
    }

    private val userDetailsAdapter by lazy {
        UserDetailsAdapter()
    }

    private val userDetailsViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(mainRepository))
            .get(UserDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        getUserRepos()
        initObserver()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.setActionBarTitle(titleResId = R.string.app_name)
    }

    private fun getUserRepos() {
        arguments?.run {
            val login = UserDetailsFragmentArgs.fromBundle(this).login
            userDetailsViewModel.getUserInfo(login)
            userDetailsViewModel.getUserRepos(login)
        }
    }

    private fun initUi() {
        recyclerViewUserRepos.apply {
            adapter = userDetailsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initObserver() {

        userDetailsViewModel.getUserInfoResult.observe(viewLifecycleOwner, Observer {
            responseList.add(it)
        })

        userDetailsViewModel.getUserReposResult.observe(viewLifecycleOwner, Observer {
            responseList.addAll(it)
            setAdapterList()
        })

    }

    private fun setAdapterList() {
        userDetailsAdapter.setItems(responseList)
    }
}
