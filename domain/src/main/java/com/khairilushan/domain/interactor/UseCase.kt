package com.khairilushan.domain.interactor

import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withContext
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by khairil on 11/6/17.
 */
abstract class UseCase<Type : Any, in Params>(
    private val bgContext: CoroutineContext,
    private val uiContext: CoroutineContext
) {

    private val job = Job()

    abstract fun build(params: Params?): Deferred<Type>

    fun dispose() = job.cancel()

    fun execute(params: Params? = null, callback: (Result<Type>) -> Unit) {
        launch(uiContext + job) {
            try {
                val result = withContext(bgContext) { build(params).await() }
                callback(Result.Success(result))
            } catch (t: Throwable) {
                callback(Result.Failure(t.hashCode(), t.localizedMessage))
            }

        }
    }
}

@Suppress("unused")
sealed class Result<out T : Any> {
    data class Success<T : Any>(val result: T) : Result<T>()
    data class Failure(val errorCode: Int, val message: String) : Result<Nothing>()
}
