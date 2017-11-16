package com.khairilushan.learningcleanarchitecture.shared.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by khairil on 11/16/17.
 */
fun Disposable.disposed(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}