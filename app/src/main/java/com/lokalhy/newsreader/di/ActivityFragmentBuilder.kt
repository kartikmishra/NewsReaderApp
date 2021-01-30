package com.lokalhy.newsreader.di

import com.lokalhy.newsreader.ui.MainActivity
import com.lokalhy.newsreader.ui.MainFragment
import com.lokalhy.newsreader.ui.NewsDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityFragmentBuilder {
    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindUsersFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun bindNewsDetailFragment(): NewsDetailFragment

}