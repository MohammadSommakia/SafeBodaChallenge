package com.safeboda.core.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
//import com.safeboda.domain.models.base.Failure


@BindingAdapter("android:textString")
fun TextView.setStringResource(text: String) {
    this.text = text
}

@BindingAdapter("navigationOnClick")
fun Toolbar.setNavigationClickListener(listener: () -> Unit) {
    this.setNavigationOnClickListener {
        listener()
    }
}

@BindingAdapter("setNavigationIcon")
fun Toolbar.setNavigationIcon(drawableRes: Int?) {
    this.navigationIcon =
        if (drawableRes == null) null else ContextCompat.getDrawable(this.context, drawableRes)
}
//
//@BindingAdapter("textFromFailure")
//fun TextView.setTextFromFailure(failure: Failure?) {
//    failure?.let {
//        this.text = failure.getErrorMessage(context)
//    }
//}

@BindingAdapter("imageUrl")
fun ImageView.setImageFromUrl(url: String?) {
    url?.let {
        Glide.with(this)
            .load(url)
            .into(this);
    }
}
