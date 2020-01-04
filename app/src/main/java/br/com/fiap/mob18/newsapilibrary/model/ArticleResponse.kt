package br.com.fiap.mob18.newsapilibrary.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticleResponse(
    @SerializedName("status") val status : String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("message") val message : String,
    @SerializedName("articles") val articles : List<Article>
) : Parcelable