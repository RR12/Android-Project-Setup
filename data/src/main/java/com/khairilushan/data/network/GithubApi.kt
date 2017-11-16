package com.khairilushan.data.network

import com.khairilushan.data.entity.RepoListEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by khairil on 11/6/17.
 */
interface GithubApi {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") query: String): Single<RepoListEntity>

}