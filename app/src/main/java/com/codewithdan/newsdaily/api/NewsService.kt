package com.codewithdan.newsdaily.api

import com.codewithdan.newsdaily.data.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "ac60a9c7505f4266a56d5373d32d8e26"

interface NewsInterface {

    @GET("v2/everything?apiKey=$API_KEY")
    fun getHeadlines(@Query("q")q: String,@Query("page") page: Int) : Call<News>

}

object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}