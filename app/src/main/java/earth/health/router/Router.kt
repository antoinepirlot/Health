package earth.health.router

import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.entity.getDefaultMeals
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodMealRelViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.screens.HomeScreen
import earth.health.screens.meal.MealHomeScreen
import earth.health.screens.meal.MealScreen
import earth.health.screens.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val dayViewModel = viewModel<DayViewModel>()
    val foodViewModel = viewModel<FoodViewModel>()
    val mealViewModel = viewModel<MealViewModel>()
    //todo doesn't print
    println(message = "MEALS --> " + mealViewModel.meals.toString())
    val foodMealRelViewModel = viewModel<FoodMealRelViewModel>()
    val meals = mealViewModel.meals

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            HomeScreen(navController, dayViewModel)
        }
        composable(Destination.MEALS.link) {
            MealHomeScreen(meals, mealViewModel) { meal ->
                navController.navigate(Destination.MEALS.link + "/${meal.id}")
            }
        }
        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments!!.getString("id")!!
            val meal = meals.first { it.id == id.toLong() }
            MealScreen(meal = meal, mealViewModel)
        }
        composable(Destination.WEIGHT.link) {
            WeightHomeScreen(navController)
        }
    }
}