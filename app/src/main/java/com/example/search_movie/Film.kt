package com.example.search_movie

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class Film(
    val title: String,
    val poster: Int,
    val description: String,
    var rating: Float = 0f,
    var isInFavorites: Boolean = false
) : Parcelable
