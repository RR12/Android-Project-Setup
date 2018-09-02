package com.khairilushan.domain.repository

import com.khairilushan.domain.model.Repo
import kotlinx.coroutines.experimental.Deferred

/**
 * Created by khairil on 11/16/17.
 */
interface GithubRepository {
    fun search(query: String): Deferred<List<Repo>>

    fun searchLocally(query: String): Deferred<List<Repo>>
}
