package com.bugrakandemir.gitappremake.ui.userinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bugrakandemir.gitappremake.R
import com.bugrakandemir.gitappremake.model.UserInfo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user_info.view.*

class UserDetailsViewHolderInfo(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatarInfoImageView = itemView.imageViewInfoAvatar
    private val loginInfoTextView = itemView.textViewInfoLogin
    private val nameInfoTextView = itemView.textViewInfoUserName
    private val bioInfoTextView = itemView.textViewInfoBio
    private val companyInfoTextView = itemView.textViewInfoCompany
    private val emailInfoTextView = itemView.textViewInfoEMail
    private val blogInfoTextView = itemView.textViewInfoBlog


    fun bind(userInfo: UserInfo) {

        Glide.with(avatarInfoImageView).load(userInfo.avatar_url).into(avatarInfoImageView)

        loginInfoTextView.text = userInfo.login
        nameInfoTextView.text = userInfo.name

        if (userInfo.company == null) {
            companyInfoTextView.text = "Not Specified"
        } else {
            companyInfoTextView.text = userInfo.company
        }
        if (userInfo.blog == null) {
            blogInfoTextView.text = "Not Specified"
        } else {
            blogInfoTextView.text = userInfo.blog
        }
        if (userInfo.bio == null) {
            bioInfoTextView.text = "Not Specified"
        } else {
            bioInfoTextView.text = userInfo.bio
        }
        if (userInfo.email == null) {
            emailInfoTextView.text = "Not Specified"
        } else {
            emailInfoTextView.text = userInfo.email
        }
    }

    companion object {
        fun create(parent: ViewGroup): UserDetailsViewHolderInfo {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_info, parent, false)
            return UserDetailsViewHolderInfo(view)
        }
    }
}