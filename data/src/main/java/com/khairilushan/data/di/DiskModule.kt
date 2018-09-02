package com.khairilushan.data.di

import android.app.Application
import android.arch.persistence.room.Room
import com.khairilushan.data.local.GithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by khairil on 11/16/17.
 */
@Module
class DiskModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): GithubDatabase =
            Room.databaseBuilder(application.applicationContext, GithubDatabase::class.java,
                    "github.db")
                    .build()
}
