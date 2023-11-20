package tees.ac.uk.Q2078619.newsapp.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tees.ac.uk.Q2078619.newsapp.services.NewsApiService.Companion.BASE_URL

object RetrofitServiceInstance {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val newsService: NewsApiService = retrofit.create(NewsApiService::class.java)
}