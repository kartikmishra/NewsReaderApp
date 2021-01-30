package com.lokalhy.newsreader.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationProviderModule(private val application: Application) {


    @Provides
    @Singleton
    fun application(): Application {
        return application
    }
}