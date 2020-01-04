package br.com.fiap.mob18.newsapilibrary.database

import android.app.Application
import androidx.room.Room


class NewsDatabaseBuilder {

    fun build(applicationContext: Application) : NewsDatabase{
        return Room.databaseBuilder(
                applicationContext,
                NewsDatabase::class.java,
            "news-database")
            .allowMainThreadQueries()
            .build()
    }

}