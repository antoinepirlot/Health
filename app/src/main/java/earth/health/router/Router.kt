package earth.health.router

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.screens.HomeScreen
import earth.health.screens.meal.MealHomeScreen
import earth.health.screens.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            HomeScreen(navController)
        }
        composable(Destination.MEALS.link) {
            MealHomeScreen(navController)
        }
        composable(Destination.WEIGHT.link) {
            WeightHomeScreen(navController)
        }
    }
}