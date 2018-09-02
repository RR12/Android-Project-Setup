package com.khairilushan.androidsetup.shared.di

import com.khairilushan.androidsetup.BuildConfig
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import javax.inject.Named
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by khairil on 11/16/17.
 */
@Module
class AppModule {

    @Provides
    @Named("is_debug")
    fun provideDebugMode(): Boolean = BuildConfig.DEBUG

    @Provides
    @Named("bg_context")
    fun provideExecutionScheduler(): CoroutineContext = CommonPool

    @Provides
    @Named("ui_context")
    fun providePostExecutionScheduler(): CoroutineContext = UI
}
