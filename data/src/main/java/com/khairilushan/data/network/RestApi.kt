package com.khairilushan.data.network

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by khairil on 11/6/17.
 */
class RestApi
@Inject constructor(retrofit: Retrofit) : GithubApi {

    private val githubApi: GithubApi by lazy { retrofit.create(GithubApi::class.java) }

    override fun searchRepositories(query: String) = githubApi.searchRepositories(query)

}