package com.lokalhy.newsreader.retrofit

import com.lokalhy.newsreader.model.NewsData
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsService {

    @GET("top-headlines?country=in&apiKey=71724ba156f743479ce312e8c6f2fc6d")
    fun getTrendingNews(): Observable<NewsData>
}