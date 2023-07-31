package earth.health.router

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.ui.HomeScreen
import earth.health.ui.meal.MealHomeScreen
import earth.health.ui.meal.MealScreen
import earth.health.ui.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val days = viewModel<DayViewModel>().days
    val mealViewModel = viewModel<MealViewModel>()
    // TODO meals are not reload after creating days
    val meals = mealViewModel.meals

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
            val id = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            val meal = meals.first { it.id == id }
            MealScreen(mealWithFoods = mealViewModel.getMealWithFoods(id))
        }
        composable(Destination.WEIGHT.link) {
            WeightHomeScreen(navController)
        }
    }
}