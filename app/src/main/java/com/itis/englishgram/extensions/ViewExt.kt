package com.itis.englishgram.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager


fun View.hideKeyboard(context: Context)
{
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.run {
        hideSoftInputFromWindow(windowToken, 0)
    }
}