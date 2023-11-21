package tees.ac.uk.Q2078619.newsapp.data.model

data class NewsArticle(
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String,
    val url: String,
    val image: String?
)


data class Source(
    val url: String?,
    val name: String?
)
