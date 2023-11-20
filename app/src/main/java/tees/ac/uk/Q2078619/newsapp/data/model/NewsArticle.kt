package tees.ac.uk.Q2078619.newsapp.data.model

data class NewsArticle(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?
)


data class Source(
    val id: String,
    val name: String?
)
