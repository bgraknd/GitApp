package com.bugrakandemir.gitappremake.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchTest(
    val avatar_url: String,
    val description: String,
    val forks_count: Int,
    val language: String,
    val login: String,
    val name: String,
    val stargazers_count: Int
) : Parcelable