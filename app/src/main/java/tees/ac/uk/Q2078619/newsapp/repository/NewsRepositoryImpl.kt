package com.example.thenewsroom.data.remote.repository

import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.repository.NewsRepository
import tees.ac.uk.Q2078619.newsapp.services.NewsApiService
import tees.ac.uk.Q2078619.newsapp.utils.Resource


class NewsRepositoryImpl(
    private val newsApi: NewsApiService
): NewsRepository {
    override suspend fun getTopHeadlines(category: String): Resource<List<NewsArticle>> {
        return try{
            val response = newsApi.TopHeadlineNews(category = category)
            Resource.Success(response.articles)
        }catch (e: Exception){
            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}