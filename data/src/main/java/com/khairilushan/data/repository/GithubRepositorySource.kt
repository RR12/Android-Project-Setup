package com.khairilushan.data.repository

import com.khairilushan.data.source.GithubDataSource
import com.khairilushan.domain.model.Repo
import com.khairilushan.domain.repository.GithubRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
class GithubRepositorySource
@Inject constructor(private val factory: GithubDataSource.Factory) : GithubRepository {
    override fun search(query: String): Single<List<Repo>> {
        return factory.network().search(query)
                .onErrorResumeNext { factory.disk().search(query) }
    }
}