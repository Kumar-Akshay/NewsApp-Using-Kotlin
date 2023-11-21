package com.example.thenewsroom.data.remote.repository

import android.util.Log
import tees.ac.uk.Q2078619.newsapp.data.model.NewsArticle
import tees.ac.uk.Q2078619.newsapp.repository.NewsRepository
import tees.ac.uk.Q2078619.newsapp.services.NewsApiService
import tees.ac.uk.Q2078619.newsapp.utils.Resource

class NewsRepositoryImpl(
    private val newsApi: NewsApiService
): NewsRepository {

    private val TAG = NewsRepositoryImpl::class.simpleName
    override suspend fun getTopHeadlines(category: String): Resource<List<NewsArticle>> {
        return try{
            val response = newsApi.TopHeadlineNews(category = category)
            Log.d(TAG,"Repo Implementation - going - "+category)
            if (!response.articles.isEmpty())
            {
                Log.d(TAG,"Repo Implementation - pass")
                Resource.Success(response.articles)
            }
            else{
                Log.d(TAG,"Repo Implementation - failed")
                Resource.Error(message = "Failed to fetch news, Try again")
            }
        }catch (e: Exception){
            Log.d(TAG,"Repo Implementation - exception")

            Resource.Error(message = "Failed to fetch news ${e.message}")
        }
    }
}