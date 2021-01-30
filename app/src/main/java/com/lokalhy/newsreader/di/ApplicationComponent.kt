package com.lokalhy.newsreader.di

import android.content.Context
import com.lokalhy.newsreader.MvRxApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        RetrofitModule::class,
        AppAssistedModule::class,
        ActivityFragmentBuilder::class
    ]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder

        fun build(): ApplicationComponent

    }

    fun inject(app: MvRxApplication)

    override fun inject(instance: DaggerApplication?)
}