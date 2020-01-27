package com.bugrakandemir.gitappremake.ui.repodetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bugrakandemir.gitappremake.MainActivity
import com.bugrakandemir.gitappremake.R
import com.bugrakandemir.gitappremake.base.BaseFragment
import com.bugrakandemir.gitappremake.model.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_repo_details.*

/**
 * A simple [Fragment] subclass.
 */
class RepoDetailsFragment : BaseFragment() {

    private lateinit var item: Item

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRepoDetails()
        imageViewRepoDetailsAvatar.setOnClickListener {
            findNavController().navigate(
                RepoDetailsFragmentDirections.actionRepoDetailsFragmentToUserDetailsFragment(item.owner.login)
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.setActionBarTitle(item.name)
    }

    private fun getRepoDetails() {
        arguments?.run {
            item = RepoDetailsFragmentArgs.fromBundle(this).item
            Glide.with(imageViewRepoDetailsAvatar).load(item.owner.avatar_url)
                .into(imageViewRepoDetailsAvatar)
            textViewRepoDetailsName.text = item.name
            textViewRepoDetailsDescription.text = item.description
            textViewRepoDetailsLanguage.text = item.language
            textViewRepoDetailsFullName.text = item.full_name
            textViewRepoDetailsBranch.text = item.default_branch
            textViewRepoDetailsStars.text = item.stargazers_count.toString()
            textViewRepoDetailsForks.text = item.forks_count.toString()
            textViewRepoDetailsWatchers.text = item.open_issues_count.toString()
            textViewRepoDetailsCreated.text = item.created_at
            textViewRepoDetailsUpdated.text = item.updated_at
            textViewRepoDetailsPushed.text = item.pushed_at
        }
    }
}

