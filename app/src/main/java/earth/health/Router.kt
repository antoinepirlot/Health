package earth.health

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.screens.HomeScreen
import earth.health.screens.meal.MealHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "/") {
        composable("/") {
            HomeScreen()
        }
    }
}