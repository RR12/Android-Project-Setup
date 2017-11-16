package com.khairilushan.domain.interactor

import com.khairilushan.domain.model.Repo
import com.khairilushan.domain.repository.GithubRepository
import io.reactivex.Scheduler
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by khairil on 11/6/17.
 */
class SearchRepository
@Inject constructor(private val githubRepository: GithubRepository,
                    @Named("execution") private val executionScheduler: Scheduler,
                    @Named("post_execution") private val postExecutionScheduler: Scheduler)
    : UseCase<List<Repo>, SearchRepository.Params>() {

    override fun build(params: Params?): Single<List<Repo>> {
        return githubRepository.search(params!!.keyword)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler)
    }

    data class Params(val keyword: String)
}