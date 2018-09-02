package com.khairilushan.data.source

import com.khairilushan.data.extension.await
import com.khairilushan.data.local.AppDatabase
import com.khairilushan.data.remote.RestApi
import com.khairilushan.domain.model.Repo
import com.khairilushan.domain.repository.GithubRepository
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
interface GithubDataSource : GithubRepository {

    class Remote @Inject constructor(
        private val restApi: RestApi,
        private val appDatabase: AppDatabase
    ) : GithubRepository {
        override fun searchLocally(query: String): Deferred<List<Repo>> {
            throw IllegalArgumentException("searchLocally not available for remote data source")
        }

        override fun search(query: String): Deferred<List<Repo>> = async {
            val entity = restApi.searchRepositories(query).await()
            entity.items.map {
                appDatabase.insertRepo(it)
                it.toRepo()
            }
        }
    }

    class Local @Inject constructor(
        private val appDatabase: AppDatabase
    ) : GithubRepository {
        override fun search(query: String): Deferred<List<Repo>> {
            throw IllegalArgumentException("search not available for local data source")
        }

        override fun searchLocally(query: String): Deferred<List<Repo>> = async {
            when (query.isEmpty() || query.isBlank()) {
                true -> appDatabase.getAllRepo().map { it.toRepo() }
                else -> appDatabase.findRepoByName(query).map { it.toRepo() }
            }
        }
    }
}
