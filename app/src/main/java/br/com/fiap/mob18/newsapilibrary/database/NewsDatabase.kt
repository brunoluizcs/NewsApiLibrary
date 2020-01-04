package br.com.fiap.mob18.newsapilibrary.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.mob18.newsapilibrary.dao.ArticleDao
import br.com.fiap.mob18.newsapilibrary.entity.ArticleEntity

@Database(entities = [ArticleEntity::class],version = 1)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase(){
    abstract fun articleDao() : ArticleDao
}