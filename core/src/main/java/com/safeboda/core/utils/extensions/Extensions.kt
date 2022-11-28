package com.safeboda.core.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.safeboda.core.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


//fun Failure.getErrorMessage(context: Context): String {
//    return when {
//        message != null -> {
//            message!!
//        }
//        messageRes != null -> {
//            context.getString(this.messageRes!!)
//        }
//        else -> {
//            "Unknown error has occurred"
//        }
//    }
//}

fun Date.getFormatted(): String {
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return format.format(this)
}

fun String.toDate(): Date? {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        format.parse(this)
    } catch (e: ParseException) {
        null
    }
}

fun View.showKeyboard() {
    this.requestFocus()
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}