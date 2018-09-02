package com.khairilushan.androidsetup.shared.extension

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity


inline fun <T> AppCompatActivity.observeViewModel(
    liveData: LiveData<T>,
    crossinline f: (data: T?) -> Unit
) {
    liveData.observe(this, Observer { f(it) })
}
