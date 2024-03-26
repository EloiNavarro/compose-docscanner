package com.eloinavarro.docscanner.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.eloinavarro.docscanner.domain.ScannedDocument

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {

    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute).plus(argKeys).joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

    object Main : NavItem("main")
    object Detail : NavItem("detail", listOf(NavArg.UUID)) {
        fun createNavRoute(scannedDocument: ScannedDocument) = "$baseRoute/${scannedDocument.id}"
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    UUID("uuid", NavType.StringType)
}