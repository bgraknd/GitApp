package com.bugrakandemir.gitappremake.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LicenseUser(
    val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String
) : Parcelable