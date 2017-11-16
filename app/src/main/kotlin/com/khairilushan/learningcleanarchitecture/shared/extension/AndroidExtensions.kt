package com.khairilushan.learningcleanarchitecture.shared.extension

import android.app.Activity
import android.content.Context
import android.widget.Toast

/**
 * Created by khairil on 11/16/17.
 */

fun Activity.showLongToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun Activity.showShortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}