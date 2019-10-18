# NewsApiLibrary
Biblioteca utilizada para consumir API de noticias

## Documentação

**fetchTopHeadlines**

Retorna as principais noticias para um país ou categoria especifica também é possível buscar por termos especificos**

```
fun fetchTopHeadlines(query: String? = null,category: Category? = null,country: String = "br", pageSize: Int = 50,page: Int = 0) : ArticleResponse
```

 * **Parâmetros:**
   * `query` — palavra chave ou frase para realizar a busca
   * `category` — categoria que o filtro deve ser aplicado
   * `pageSize` — O número de resultados por requisição
   * `page` — A página que deseja requisitar se o número de total de resposta for maior que o número de páginas

**fetchEverything**

Pesquisa atraves de todos artigos e noticias incluindo artigos de pequenos blogs**

```
fun fetchEverything(query: String? = null,queryInTitle: String? = null,from: Date? = null,to: Date? = null, sortBy: Sorter? = null,language: String = "pt",pageSize: Int = 50,page: Int = 0) : ArticleResponse
```

 * **Parâmetros:**
   * `query` — palavra chave ou frase para realizar a busca
   * `queryInTitle` — palavra chave ou frase para realizar a busca no titulo
   * `from` — data opicional do artigo mais antigo
   * `to` — data opicional do artigo mais recente
   * `sortBy` — criterio de ordenação
   * `language` — Código  ISO-639-1 da linguagem que deseja buscar
   * `pageSize` — O número de resultados por requisição
   * `page` — A página que deseja requisitar se o número de total de resposta for maior que o número de páginas
   

### Exemplo
```
val googleApi = GoogleApiBuilder()
            .withApiKey("")
            .build()
            
googleApi.fetchTopHeadlines()
```

#### Projetos
  * [Stay Informed Android](https://github.com/GustavoCaspirro/stay-informed-android)


