package com.bugrakandemir.gitappremake.ui.searchpage

import androidx.recyclerview.widget.DiffUtil
import com.bugrakandemir.gitappremake.model.Item

class DiffUtilCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }

}