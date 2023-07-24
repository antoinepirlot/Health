package earth.health.router

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.entity.defaultMealList
import earth.health.screens.HomeScreen
import earth.health.screens.meal.MealHomeScreen
import earth.health.screens.meal.MealScreen
import earth.health.screens.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val meals = defaultMealList
    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            HomeScreen(navController)
        }
        composable(Destination.MEALS.link) {
            MealHomeScreen(navController)
        }
        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments!!.getString("id")!!
            val meal = meals.first { it.id == id.toLong() }
            MealScreen(meal = meal)
        }
        composable(Destination.WEIGHT.link) {
            WeightHomeScreen(navController)
        }
    }
}