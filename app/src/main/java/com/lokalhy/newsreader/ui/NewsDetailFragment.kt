package com.lokalhy.newsreader.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.*
import com.lokalhy.newsreader.NewsVM
import com.lokalhy.newsreader.R
import com.squareup.picasso.Picasso


class NewsDetailFragment : BaseMvRxFragment() {


    private val newsVM: NewsVM by activityViewModel()

    lateinit var iv_article:ImageView
    lateinit var tv_title:TextView
    lateinit var tv_desc:TextView
    lateinit var tv_content:TextView
    lateinit var tv_source:TextView
    lateinit var tv_author:TextView
    lateinit var tv_publishedAt:TextView


    override fun invalidate() {
        withState(newsVM) {
           Picasso.get().load(it.newsArticle.urlToImage).fit().into(iv_article)
           tv_title.text = it.newsArticle.title
            tv_desc.text = it.newsArticle.description
            tv_content.text = it.newsArticle.content
            tv_source.text = it.newsArticle.source.name
            if(it.newsArticle.author != null) {
                tv_author.text = "By ${it.newsArticle.author}"
            }

            tv_publishedAt.text = it.newsArticle.publishedAt
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         iv_article = view.findViewById(R.id.iv_news)
         tv_title = view.findViewById(R.id.tv_title)
         tv_content = view.findViewById(R.id.tv_content)
         tv_desc = view.findViewById(R.id.tv_desc)
         tv_source = view.findViewById(R.id.tv_source)

        tv_author = view.findViewById(R.id.tv_author)
        tv_publishedAt = view.findViewById(R.id.tv_publishedAt)

        view.findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            findNavController().navigateUp()
        }
    }

}