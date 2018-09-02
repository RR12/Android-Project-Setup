package com.khairilushan.data.repository

import com.khairilushan.data.source.GithubDataSource
import com.khairilushan.domain.model.Repo
import com.khairilushan.domain.repository.GithubRepository
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
class GithubRepositoryImpl @Inject constructor(
    private val remote: GithubDataSource.Remote,
    private val local: GithubDataSource.Local
) : GithubRepository {

    override fun searchLocally(query: String): Deferred<List<Repo>> = local.searchLocally(query)

    override fun search(query: String): Deferred<List<Repo>> = try {
        remote.search(query)
    } catch (t: Throwable) {
        local.searchLocally(query)
    }

}
