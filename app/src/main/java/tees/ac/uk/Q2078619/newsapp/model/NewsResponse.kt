package tees.ac.uk.Q2078619.newsapp.model

data class NewsResponse(
    val articles: List<NewsArticle>,
    val status: String,
    val totalResults: Int
)