package com.bugrakandemir.gitappremake.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchMain(
    val incomplete_results: Boolean,
    val items: List<Item>,
    val total_count: Int
) : Parcelable