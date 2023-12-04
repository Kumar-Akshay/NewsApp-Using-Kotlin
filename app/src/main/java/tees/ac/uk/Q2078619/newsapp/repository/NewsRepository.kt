package tees.ac.uk.Q2078619.newsapp.repository

import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.utils.Resource

interface NewsRepository {

    suspend fun getTopHeadlines(
        category: String,
        country: String
    ): Resource<List<NewsArticle>>
}