package br.com.fiap.mob18.newsapilibrary.api

import br.com.fiap.mob18.newsapilibrary.model.Article
import br.com.fiap.mob18.newsapilibrary.model.ArticleResponse
import br.com.fiap.mob18.newsapilibrary.model.Category
import br.com.fiap.mob18.newsapilibrary.model.Sorter
import java.util.*


interface NewsApi {

    /**
     * Retorna as principais noticias para um país ou categoria especifica também é possível buscar por
     * termos especificos
     * @param query palavra chave ou frase para realizar a busca
     * @param category categoria que o filtro deve ser aplicado
     * @oaram country código ISO 3166-1 com 2 letras do país que você deseja recuperar as noticias
     * @param pageSize O número de resultados por requisição
     * @param page A página que deseja requisitar se o número de total de resposta for maior que o número de páginas
     */
    fun fetchTopHeadlines(query: String? = null,category: Category? = null,country: String = "br",
                          pageSize: Int = 50,page: Int = 0) : ArticleResponse

    /**
     * Pesquisa atraves de todos artigos e noticias incluindo artigos de pequenos blogs
     * @param query palavra chave ou frase para realizar a busca
     * @param queryInTitle palavra chave ou frase para realizar a busca no titulo
     * @param from data opicional do artigo mais antigo
     * @param to data opicional do artigo mais recente
     * @param sortBy criterio de ordenação
     * @param language Código  ISO-639-1 da linguagem que deseja buscar
     * @param pageSize O número de resultados por requisição
     * @param page A página que deseja requisitar se o número de total de resposta for maior que o número de páginas
     */
    fun fetchEverything(query: String? = null,queryInTitle: String? = null,from: Date? = null,to: Date? = null,
                        sortBy: Sorter? = null,language: String = "pt",pageSize: Int = 50,page: Int = 0) : ArticleResponse


    /**
     * Lista os artigos favoritos
     */
    fun fetchFavorites() : ArticleResponse

    /**
     * Adiciona um artigo na lista de favoritos
     * @param article artigo que deseja marcar como favorito
     */
    fun addFavorite(article: Article)

    /**
     * Remove um artigo da lista de favoritos
     * @param article artigo que deseja remover da lista de favorito
     */
    fun removeFavorite(article: Article)


}