package earth.health.router

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.entity.relations.MealWithFoods
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.ui.HomeScreen
import earth.health.ui.food.AddFoodScreen
import earth.health.ui.food.AllFoodsScreen
import earth.health.ui.food.FoodScreen
import earth.health.ui.meal.MealHomeScreen
import earth.health.ui.meal.MealScreen
import earth.health.ui.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val days = viewModel<DayViewModel>().days
    val mealViewModel = viewModel<MealViewModel>()
    val mealWithFoods = mealViewModel.mealWithFoods
    val foodViewModel = viewModel<FoodViewModel>()
    val foodList = foodViewModel.foodList

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            HomeScreen(navController = navController, days = days)
        }
        composable(Destination.MEALS.link) {
            MealHomeScreen(mealsWithFoods = mealWithFoods) { meal ->
                navController.navigate(Destination.MEALS.link + "/${meal.id}")
            }
        }
        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            val mealId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            MealScreen(
                mealWithFoods = mealWithFoods.first() { it.meal.id == mealId },
                addAction = { navController.navigate(Destination.FOODS.link) },
                textAction = { navController.navigate(Destination.FOODS.link + "/${it.id}") }
            )
        }
        composable(Destination.FOODS.link) {
            AllFoodsScreen(
                foodList = foodList,
                actionOpenFood =  { food ->
                    navController.navigate(Destination.FOODS.link + "/${food.food.id}")
                }
            )
        }
        composable(Destination.FOODS.link + "/{id}") {navBackStackEntry ->
            val foodId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            FoodScreen(food = foodList.first() { it.food.id == foodId })
        }
        composable(Destination.WEIGHT.link) {
            WeightHomeScreen(navController)
        }
        composable(Destination.ADD_FOOD_SCREEN.link) {
            AddFoodScreen()
        }
    }
}