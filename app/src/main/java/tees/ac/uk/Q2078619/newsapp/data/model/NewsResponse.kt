package tees.ac.uk.Q2078619.newsapp.data.model

data class NewsResponse(
    val articles: List<NewsArticle>,
    val totalArticles: Int
)