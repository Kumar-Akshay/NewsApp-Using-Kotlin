package tees.ac.uk.Q2078619.newsapp.repository

import tees.ac.uk.Q2078619.newsapp.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.utils.Resource

interface NewsRepository {
    suspend fun getTopHeadlines(
        category: String
    ): Resource<List<NewsArticle>>
    suspend fun getTechnologyNews(
        category: String
    ): Resource<List<NewsArticle>>
    suspend fun getSportNews(
        category: String
    ): Resource<List<NewsArticle>>
    suspend fun getEntertainmentNews(
        category: String
    ): Resource<List<NewsArticle>>

}