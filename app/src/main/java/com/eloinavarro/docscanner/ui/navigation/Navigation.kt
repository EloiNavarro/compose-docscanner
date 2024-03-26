package com.eloinavarro.docscanner.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eloinavarro.docscanner.ui.screens.detail.DetailScreen
import com.eloinavarro.docscanner.ui.screens.overview.OverviewScreen
import java.util.UUID

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main) {
            OverviewScreen(onItemClick = { scannedDocument ->
                navController.navigate(NavItem.Detail.createNavRoute(scannedDocument))
                Log.d("DEBUG", "Send UUID:${scannedDocument.id}")
            })
        }
        composable(NavItem.Detail) { backStackEntry ->
            val uuid = backStackEntry.findArg<String>(NavArg.UUID)
            Log.d("DEBUG", "Receive UUID:$uuid")
            DetailScreen(
                uuid = UUID.fromString(uuid),
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