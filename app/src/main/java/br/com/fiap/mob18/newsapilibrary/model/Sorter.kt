package br.com.fiap.mob18.newsapilibrary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Sorter(val value : String) : Parcelable {
    Relevancy("relevancy"),
    Popularity("popularity"),
    PublishedAt("publishedAt")


}