package br.com.fiap.mob18.newsapilibrary.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.mob18.newsapilibrary.entity.ArticleEntity

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAll(): List<ArticleEntity>

    @Query("SELECT * FROM article where title = :title")
    fun getByTitle(title: String) : List<ArticleEntity>

    @Insert
    fun insertAll(vararg articles: ArticleEntity)

    @Query("DELETE FROM article where title=:title")
    fun delete(title: String)

}