package com.bugrakandemir.gitappremake.ui.searchpage

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugrakandemir.gitappremake.model.Item
import kotlinx.android.synthetic.main.item_search_page.view.*

class SearchPageAdapter(
    private val onItemClickCardView: (Item) -> Unit,
    private val onItemClickAvatar: (Item) -> Unit
) : RecyclerView.Adapter<SearchPageViewHolder>() {

    private val items = mutableListOf<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPageViewHolder {
        return SearchPageViewHolder.create(parent).apply {
            itemView.setOnClickListener { onItemClickCardView(items[adapterPosition]) }
            itemView.imageViewSearchAvatar.setOnClickListener { onItemClickAvatar(items[adapterPosition]) }
        }
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: SearchPageViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<Item>) {
        items.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }
}
