package com.example.submission2.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    var avatar: String? = "",
    var username: String? = "",
    var location: String? = "",
    var name: String? = "",
    var company: String? = "",
    var repository: Int? = 0,
    var followers: Int? = 0,
    var following: Int? = 0
) : Parcelable

