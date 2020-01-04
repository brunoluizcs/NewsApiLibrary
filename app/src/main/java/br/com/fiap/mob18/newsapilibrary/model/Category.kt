package br.com.fiap.mob18.newsapilibrary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Category(val value: String) : Parcelable {
    Business("business"),
    Entertainment("entertainment"),
    General("general"),
    Health("health"),
    Science("science"),
    Sports("sports"),
    Technology("technology")
}