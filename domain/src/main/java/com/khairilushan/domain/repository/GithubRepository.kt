package com.khairilushan.domain.repository

import com.khairilushan.domain.model.Repo
import io.reactivex.Single

/**
 * Created by khairil on 11/16/17.
 */
interface GithubRepository {
    fun search(query: String): Single<List<Repo>>
}