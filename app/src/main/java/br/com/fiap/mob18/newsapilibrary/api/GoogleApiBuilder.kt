package br.com.fiap.mob18.newsapilibrary.api

import android.annotation.SuppressLint
import android.app.Application
import br.com.fiap.mob18.newsapilibrary.database.NewsDatabaseBuilder
import br.com.fiap.mob18.newsapilibrary.model.*
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.text.SimpleDateFormat
import java.util.*


class GoogleApiBuilder {
    private val baseUrl : String = "https://newsapi.org/v2"
    private var _apiKey : String = ""
    private var client  : OkHttpClient = OkHttpClient()
    private var gson = GsonBuilder().setDateFormat("YYYY-MM-DD'T'HH:mm:ss").create()


    fun withGsonConverter(gson: Gson) : GoogleApiBuilder{
        this.gson = gson
        return this
    }

    fun withApiKey(apiKey: String) : GoogleApiBuilder{
        this._apiKey = apiKey
        return this
    }

    fun withClient(client: OkHttpClient) : GoogleApiBuilder{
        this.client = client
        return this
    }


    fun build(application: Application) : NewsApi{
        val db = NewsDatabaseBuilder().build(application)

        return object: NewsApi{
            override fun fetchFavorites(): ArticleResponse {
                val articles = db.articleDao().getAll().map {
                    it.parseToArticle()
                }
                return ArticleResponse("",articles.size,"",articles)
            }

            override fun addFavorite(article: Article) {
                db.articleDao().insertAll(article.parseToEntity())
            }

            override fun removeFavorite(article: Article) {
                db.articleDao().delete(article.title)
            }

            override fun fetchTopHeadlines(query: String?,category: Category?,
                                           country: String,pageSize: Int,page: Int): ArticleResponse {

                val topHeadLinesUrl = "$baseUrl/top-headlines"
                val httpBuilder = topHeadLinesUrl.toHttpUrlOrNull()?.newBuilder()

                httpBuilder?.also { p ->
                    query?.let { p.addQueryParameter("q",it) }
                    category?.let {p.addQueryParameter("category", it.value)}
                    country.let {p.addQueryParameter("country",it)}
                    pageSize.let {p.addQueryParameter("pageSize",it.toString())}
                    page.let {p.addQueryParameter("page",it.toString())}
                    p.addQueryParameter("apiKey",_apiKey)
                }

                val request = Request.Builder()
                    .url(httpBuilder!!.build())
                    .addHeader("content-type","application/json")
                    .build()

                client.newCall(request).execute()
                    .use {
                        response -> val articles = gson.fromJson(response.body?.string(),ArticleResponse::class.java)
                            articles.articles.map {
                                it.isFavorite = db.articleDao().getByTitle(it.title).isNotEmpty()
                            }
                        return articles
                    }
            }

            @SuppressLint("SimpleDateFormat")
            override fun fetchEverything(query: String?,queryInTitle: String?,from: Date?,to: Date?,
                                         sortBy: Sorter?,language: String,pageSize: Int,page: Int): ArticleResponse {

                val everythingUrl = "$baseUrl/everything"
                val httpBuilder = everythingUrl.toHttpUrlOrNull()?.newBuilder()

                httpBuilder?.also {p ->
                    query?.let { p.addQueryParameter("q",it) }
                    queryInTitle?.let {p.addQueryParameter("qInTitle", it)}
                    from?.let {p.addQueryParameter("from",SimpleDateFormat("YYYY-MM-DD").format(it))}
                    to?.let {p.addQueryParameter("to",SimpleDateFormat("YYYY-MM-DD").format(it))}
                    sortBy?.let {p.addQueryParameter("sortBy",it.value)}

                    p.addQueryParameter("apiKey",_apiKey)
                    p.addQueryParameter("language",language)
                    p.addQueryParameter("pageSize",pageSize.toString())
                    p.addQueryParameter("page",page.toString())
                }

                val request = Request.Builder()
                    .url(httpBuilder!!.build())
                    .addHeader("content-type","application/json")
                    .build()

                client.newCall(request).execute()
                    .use {
                        response -> val articles = gson.fromJson(response.body?.string(),ArticleResponse::class.java)
                        articles.articles.map {
                            it.isFavorite = db.articleDao().getByTitle(it.title).isNotEmpty()
                        }
                        return articles
                    }
            }
        }
    }


}