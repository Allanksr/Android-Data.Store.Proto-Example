package allanksr.com.datastore_example.ui.nav_graph

import allanksr.com.datastore_example.ui.screens.DataProtoScreen
import allanksr.com.datastore_example.ui.screens.DataStoreScreen
import allanksr.com.datastore_example.ui.screens.MainScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraphController() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.MainScreen.route
    ) {
        composable(
            route = Route.MainScreen.route
        ) {
            MainScreen(
                navController = navController
            )
        }

        composable(
            route = Route.DataStoreScreen.route
        ) {
            DataStoreScreen(
                navController = navController
            )
        }

        composable(
            route = Route.DataProtoScreen.route
        ) {
            DataProtoScreen(
                navController = navController
            )
        }
    }
}