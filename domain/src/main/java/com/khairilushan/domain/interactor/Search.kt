package com.khairilushan.domain.interactor

import com.khairilushan.domain.model.Repo
import com.khairilushan.domain.repository.GithubRepository
import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by khairil on 11/6/17.
 */
class Search @Inject constructor(
    private val githubRepository: GithubRepository,
    @Named("bg_context") bgContext: CoroutineContext,
    @Named("ui_context") uiContext: CoroutineContext
) : UseCase<List<Repo>, Search.Params>(bgContext, uiContext) {

    override fun build(params: Params?): Deferred<List<Repo>> {
        if (params == null) throw IllegalArgumentException("params should not be null")
        return githubRepository.search(params.keyword)
    }

    data class Params(val keyword: String)
}
