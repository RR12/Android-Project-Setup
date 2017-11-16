package com.khairilushan.domain.interactor

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by khairil on 11/6/17.
 */
abstract class UseCase<Type, in Params> {

    private val disposables = CompositeDisposable()

    abstract fun build(params: Params?): Single<Type>

    fun dispose() = disposables.dispose()

    fun execute(onSuccess: (Type) -> Unit, onError: (Throwable) -> Unit, params: Params? = null) {
        disposables.add(build(params).subscribe(onSuccess, onError))
    }
}