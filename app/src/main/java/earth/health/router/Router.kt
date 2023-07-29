package earth.health.router

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.ui.HomeScreen
import earth.health.ui.meal.MealHomeScreen
import earth.health.ui.meal.MealScreen
import earth.health.ui.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val dayViewModel = viewModel<DayViewModel>()
    val foodViewModel = viewModel<FoodViewModel>()
    val mealViewModel = viewModel<MealViewModel>()
    val meals = mealViewModel.meals
    val days = dayViewModel.days

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            HomeScreen(navController, days)
        }
        composable(Destination.MEALS.link) {
            MealHomeScreen(meals) { meal ->
                navController.navigate(Destination.MEALS.link + "/${meal.id}")
            }
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