package com.safeboda.core.utils.extensions

import android.app.Activity
import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.safeboda.core.R


fun String.setPathParameter(key: String, value: String): String {
    return replace("{$key}", value)
}

fun NavController.navigateAndPopStack(deepLink: Uri) {
    val options = NavOptions.Builder()
        .setLaunchSingleTop(true)
        .setPopUpTo(R.id.main_nav_graph, true)
        .build()
    navigate(deepLink, options)
}

/**
 * Use this extension function to get the main nav controller using the activity instance to access the screens in the main nav graph.
 */
fun Activity.getMainNavController() = Navigation.findNavController(this, R.id.main_nav_host_fragment)