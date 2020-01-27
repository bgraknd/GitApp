package com.bugrakandemir.gitappremake.ui.searchpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugrakandemir.gitappremake.R
import com.bugrakandemir.gitappremake.model.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search_page.view.*

class SearchPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatarImageView = itemView.imageViewSearchAvatar
    private val repoNameTextView = itemView.textViewSearchRepoName
    private val descriptionTextView = itemView.textViewSearchDescription
    private val languageTextView = itemView.textViewSearchLanguage
    private val starredTextView = itemView.textViewSearchStarred
    private val forkedTextView = itemView.textViewSearchForked
    private val userNameTextView = itemView.textViewSearchUserName



    fun bind(search: Item) {
        Glide.with(avatarImageView).load(search.owner.avatar_url).into(avatarImageView)
        repoNameTextView.text = search.name
        descriptionTextView.text = search.description
        languageTextView.text = search.language
        starredTextView.text = search.stargazers_count.toString()
        forkedTextView.text = search.forks_count.toString()
        userNameTextView.text = search.owner.login
    }

    companion object {
        fun create(parent: ViewGroup): SearchPageViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search_page, parent, false)
            return SearchPageViewHolder(view)
        }
    }
}