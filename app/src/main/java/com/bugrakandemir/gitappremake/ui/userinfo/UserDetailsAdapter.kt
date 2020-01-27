package com.bugrakandemir.gitappremake.ui.userinfo

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugrakandemir.gitappremake.model.UserInfo
import com.bugrakandemir.gitappremake.model.UserRepos

class UserDetailsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_1 = 1  // user detail
    private val TYPE_2 = 2  // user repo

    private val items = mutableListOf<BaseInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder { //3
        return when (viewType) {
            TYPE_1 -> {
                UserDetailsViewHolderInfo.create(parent)
            }
            TYPE_2 -> {
                UserDetailsViewHolderRepos.create(parent)
            }
            else -> UserDetailsViewHolderRepos.create(parent)
        }
    }

    override fun getItemCount(): Int = items.count()


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) { //4
        when (getItemViewType(position)) {
            TYPE_1 -> {
                val vHolder = holder as UserDetailsViewHolderInfo
                vHolder.bind(items[position] as UserInfo)
            }
            TYPE_2 -> {
                val vHolder = holder as UserDetailsViewHolderRepos
                vHolder.bind(items[position] as UserRepos)
            }
        }
    }

    fun setItems(newItems: List<BaseInfo>) { //1
        items.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int { //2
        return when (items[position]) {
            is UserInfo -> {
                TYPE_1
            }
            is UserRepos -> {
                TYPE_2
            }
            else -> 2
        }
    }


}