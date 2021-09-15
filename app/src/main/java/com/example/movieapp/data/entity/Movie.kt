package com.example.movieapp.data.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Long?,
    val type: String?,
    val url: String?,
    val name: String?
) : Parcelable

