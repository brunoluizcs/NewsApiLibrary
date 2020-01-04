package br.com.fiap.mob18.newsapilibrary.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.fiap.mob18.newsapilibrary.model.Article
import br.com.fiap.mob18.newsapilibrary.model.Source
import java.util.*

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val author: String? = "",
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: Date,
    val content: String
) {

    fun parseToArticle() : Article{
        return Article(
            id,
            Source("0","database"),
            author ?: "",
            title,
            description,
            url,
            urlToImage ?: "",
            publishedAt,
            content,
            true
        )
    }
}