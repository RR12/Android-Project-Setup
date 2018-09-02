package com.khairilushan.androidsetup.shared.di

import com.khairilushan.androidsetup.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by khairil on 11/6/17.
 */

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun bindSearchRepoActivity(): SearchActivity

}
