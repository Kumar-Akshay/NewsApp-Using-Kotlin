package tees.ac.uk.Q2078619.newsapp.services

import retrofit2.http.GET
import retrofit2.http.Query
import tees.ac.uk.Q2078619.newsapp.data.model.NewsResponse

interface NewsApiService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "75c62a8c721c4bf087a5e71c5202bade"
    }

    @GET("top-headlines")
    suspend fun TopHeadlineNews(
        @Query("category") category: String,
        @Query("country") country: String = "us",
        @Query("apikey") apikey: String = API_KEY,
    ): NewsResponse
}