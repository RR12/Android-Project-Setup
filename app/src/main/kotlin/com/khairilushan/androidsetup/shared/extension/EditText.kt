package com.khairilushan.androidsetup.shared.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

inline fun EditText.doAfterTextChanged(crossinline f: (text: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            f(p0?.toString().orEmpty())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    })
}

