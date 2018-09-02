package com.khairilushan.androidsetup.shared.di

import android.app.Application
import com.khairilushan.data.di.DiskModule
import com.khairilushan.data.di.NetworkModule
import com.khairilushan.data.di.RepositoryModule
import com.khairilushan.androidsetup.application.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by khairil on 11/6/17.
 */

@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        BuilderModule::class,
        NetworkModule::class,
        DiskModule::class,
        RepositoryModule::class,
        AppModule::class
))
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: App)

}
