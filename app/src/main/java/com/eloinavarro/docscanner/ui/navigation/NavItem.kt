package com.eloinavarro.docscanner.ui.navigation

import android.net.Uri
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NavArg> = emptyList()
    ) {

    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute).plus(argKeys).joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Main : NavItem("main")
    object Detail : NavItem("detail", listOf(NavArg.Uri)) {
        fun createNavRoute(uri: Uri) = "$baseRoute/$uri"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    Uri("uri", NavType.StringType)
}