package tees.ac.uk.Q2078619.newsapp.repository

import tees.ac.uk.Q2078619.newsapp.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.services.NewsApiService
import tees.ac.uk.Q2078619.newsapp.utils.Resource


class NewsRepositoryImpl(
    private val newsApi: NewsApiService
): NewsRepository {
    override suspend fun getTopHeadlines(category: String): Resource<List<NewsArticle>> {
        return try{
            val response = newsApi.getBreakingNews(category = category)
            Resource.Success(response.articles)
        }catch (e: Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }

    override suspend fun getTechnologyNews(category: String): Resource<List<NewsArticle>> {
        TODO("Not yet implemented")
    }

    override suspend fun getSportNews(category: String): Resource<List<NewsArticle>> {
        TODO("Not yet implemented")
    }

    override suspend fun getEntertainmentNews(category: String): Resource<List<NewsArticle>> {
        TODO("Not yet implemented")
    }
}