package com.lokalhy.newsreader.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsData(var status:String = "", var articles:List<Article> = emptyList()) {
}

data class Article(var source:Source = Source(""), var author:String = "",
                   var title: String = "", var description:String = "", var urlToImage:String="",
                   var publishedAt:String = "", var content:String = ""
)

data class Source(var name:String="")