package com.bugrakandemir.gitappremake.ui.searchpage

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.bugrakandemir.gitappremake.model.Item
import kotlinx.android.synthetic.main.item_search_page.view.*

class SearchPageAdapter(
    private val onItemClickCardView: (Item) -> Unit,
    private val onItemClickAvatar: (Item) -> Unit
) : PagedListAdapter<Item, SearchPageViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPageViewHolder {
        return SearchPageViewHolder.create(parent).apply {
            itemView.setOnClickListener { onItemClickCardView(getItem(adapterPosition)!!) }
            itemView.imageViewSearchAvatar.setOnClickListener {
                onItemClickAvatar(getItem(adapterPosition)!!)
            }
        }
    }

    override fun onBindViewHolder(holder: SearchPageViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}
