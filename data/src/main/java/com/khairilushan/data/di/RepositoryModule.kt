package com.khairilushan.data.di

import com.khairilushan.data.repository.GithubRepositorySource
import com.khairilushan.domain.repository.GithubRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by khairil on 11/16/17.
 */
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideGithubRepository(source: GithubRepositorySource): GithubRepository = source
}