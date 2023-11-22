package tees.ac.uk.Q2078619.newsapp.services

import retrofit2.http.GET
import retrofit2.http.Query
import tees.ac.uk.Q2078619.newsapp.data.model.NewsResponse

interface NewsApiService {

    companion object {
        const val BASE_URL = "https://gnews.io/api/v4/"
        const val API_KEY = "74b28b58b45a5f19f51a3923fb87f105"
    }

    @GET("top-headlines")
    suspend fun TopHeadlineNews(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apikey") apikey: String = API_KEY,
    ): NewsResponse
}