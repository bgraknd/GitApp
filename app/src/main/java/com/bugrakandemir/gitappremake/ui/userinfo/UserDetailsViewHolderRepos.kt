package com.bugrakandemir.gitappremake.ui.userinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugrakandemir.gitappremake.R
import com.bugrakandemir.gitappremake.model.UserRepos
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user_repos.view.*

class UserDetailsViewHolderRepos(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatarImageView = itemView.imageViewUserRepoAvatar
    private val repoNameTextView = itemView.textViewUserRepoRepoName
    private val descriptionTextView = itemView.textViewUserRepoDescription
    private val languageTextView = itemView.textViewUserRepoLanguage
    private val starredTextView = itemView.textViewUserRepoStarred
    private val forkedTextView = itemView.textViewUserRepoForked
    private val userNameTextView = itemView.textViewUserRepoUserName


    fun bind(userRepos: UserRepos) {
        Glide.with(itemView.context).load(userRepos.owner.avatar_url).into(avatarImageView)
        repoNameTextView.text = userRepos.name
        descriptionTextView.text = userRepos.description
        languageTextView.text = userRepos.language
        starredTextView.text = userRepos.stargazers_count.toString()
        forkedTextView.text = userRepos.forks_count.toString()
        userNameTextView.text = userRepos.owner.login
    }

    companion object {
        fun create(parent: ViewGroup): UserDetailsViewHolderRepos {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_repos, parent, false)
            return UserDetailsViewHolderRepos(view)
        }
    }
}