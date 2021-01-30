package com.lokalhy.newsreader

import com.airbnb.mvrx.*
import com.lokalhy.newsreader.base.MvrxViewModel
import com.lokalhy.newsreader.model.Article
import com.lokalhy.newsreader.model.NewsData
import com.lokalhy.newsreader.retrofit.NewsService
import com.lokalhy.newsreader.ui.MainActivity
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class NewsVM @AssistedInject constructor(
    @Assisted initialState: NewsState,
    private val newsService: NewsService
) : MvrxViewModel<NewsState>(initialState){


    init {
        fetchTrendingNews()
    }

    fun fetchTrendingNews() {
        newsService
            .getTrendingNews()
            .execute { copy(newsData = it) }
    }

    fun setNewsArticle(newsArticle: Article) {
        setState { copy(newsArticle = newsArticle) }
    }


    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: NewsState): NewsVM
    }

    companion object : MvRxViewModelFactory<NewsVM, NewsState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: NewsState
        ): NewsVM? {
            val activity = (viewModelContext as ActivityViewModelContext).activity<MainActivity>()
            return activity.viewModelFactory.create(state)
        }
    }

}

data class NewsState(val newsData: Async<NewsData> = Uninitialized,
                      val newsArticle:Article = Article()
): MvRxState
