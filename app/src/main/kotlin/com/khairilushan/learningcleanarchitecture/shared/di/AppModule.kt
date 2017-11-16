package com.khairilushan.learningcleanarchitecture.shared.di

import com.khairilushan.learningcleanarchitecture.BuildConfig
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by khairil on 11/16/17.
 */
@Module
class AppModule {

    @Provides
    @Named("is_debug")
    fun provideDebugMode(): Boolean = BuildConfig.DEBUG

    @Provides
    @Named("execution")
    fun provideExecutionScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Named("post_execution")
    fun providePostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()
}