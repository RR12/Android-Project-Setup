package com.khairilushan.data.source

import com.khairilushan.data.disk.AppDatabase
import com.khairilushan.data.network.RestApi
import com.khairilushan.domain.model.Repo
import com.khairilushan.domain.repository.GithubRepository
import dagger.Lazy
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
interface GithubDataSource : GithubRepository {

    class Factory
    @Inject constructor(private val network: Lazy<Network>, private val disk: Lazy<Disk>) {
        fun network(): Network = network.get()
        fun disk(): Disk = disk.get()
    }

    class Network
    @Inject constructor(private val restApi: RestApi,
                        private val appDatabase: AppDatabase) : GithubRepository {
        override fun search(query: String): Single<List<Repo>> {
            return restApi.searchRepositories(query).map { result ->
                result.items.map {
                    appDatabase.insertRepo(it)
                    it.toRepo()
                }
            }
        }
    }

    class Disk
    @Inject constructor(private val appDatabase: AppDatabase) : GithubRepository {
        override fun search(query: String): Single<List<Repo>> {
            return when (query.isEmpty() || query.isBlank()) {
                true -> appDatabase.getAllRepo().map { result ->
                    result.map { it.toRepo() }
                }
                else -> appDatabase.findRepoByName(query).map { result ->
                    result.map { it.toRepo() }
                }
            }
        }
    }
}