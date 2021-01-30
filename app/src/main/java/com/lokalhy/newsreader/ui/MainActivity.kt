package com.lokalhy.newsreader.ui

import android.os.Bundle
import com.airbnb.mvrx.BaseMvRxActivity
import com.lokalhy.newsreader.NewsVM
import com.lokalhy.newsreader.R
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : BaseMvRxActivity() {
    @Inject
    lateinit var viewModelFactory: NewsVM.Factory
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}