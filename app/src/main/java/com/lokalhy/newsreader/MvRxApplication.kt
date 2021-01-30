package com.lokalhy.newsreader

import com.lokalhy.newsreader.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MvRxApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent
            .builder()
            .applicationContext(this)
            .build()
            .also {
                it.inject(this)
            }
}