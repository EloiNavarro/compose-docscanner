package com.eloinavarro.docscanner.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eloinavarro.docscanner.ui.screens.detail.DetailScreen
import com.eloinavarro.docscanner.ui.screens.overview.OverviewScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main) {
            OverviewScreen { uri ->
                navController.navigate(NavItem.Detail.createNavRoute(uri))
            }
        }
        composable(NavItem.Detail) { backStackEntry ->
            DetailScreen(
                uri = backStackEntry.findArg(NavArg.Uri),
                onUpClick = { navController.popBackStack() }
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args,
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value) { "Missing value for key ${arg.key}" }
    return value as T
}