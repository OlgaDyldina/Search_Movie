package com.example.search_movie.domain

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class Film(
    val title: String,
    val poster: String,
    val description: String,
    var rating: Double = 0.0,
    var isInFavorites: Boolean = false
) : Parcelable
