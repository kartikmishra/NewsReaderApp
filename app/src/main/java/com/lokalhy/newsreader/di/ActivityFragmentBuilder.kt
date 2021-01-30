package com.lokalhy.newsreader.di

import com.lokalhy.newsreader.MainActivity
import com.lokalhy.newsreader.MainFragment
import com.lokalhy.newsreader.NewsDetailFragment
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