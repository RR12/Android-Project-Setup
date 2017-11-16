package com.khairilushan.learningcleanarchitecture.shared.di

import com.khairilushan.learningcleanarchitecture.search.SearchRepositoryActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by khairil on 11/6/17.
 */

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun bindSearchRepoActivity(): SearchRepositoryActivity

}