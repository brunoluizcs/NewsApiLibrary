package br.com.fiap.mob18.newsapilibrary.model

import android.os.Parcelable
import br.com.fiap.mob18.newsapilibrary.entity.ArticleEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Article(
    @SerializedName("id") val id: Int? = 0,
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String? = "",
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("publishedAt") val publishedAt: Date,
    @SerializedName("content") val content: String?,
    @SerializedName("isFavorite") var isFavorite : Boolean
) : Parcelable{

    fun parseToEntity() : ArticleEntity{
        return ArticleEntity(id?:0,author?:"",title,description,url,urlToImage,publishedAt,content?:"")
    }
}