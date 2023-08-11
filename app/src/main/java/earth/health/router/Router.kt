package earth.health.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.ui.HomeScreen
import earth.health.ui.food.AddFoodScreen
import earth.health.ui.food.AllFoodsScreen
import earth.health.ui.food.FoodScreen
import earth.health.ui.meal.AddSelectedFoodToMeal
import earth.health.ui.meal.MealHomeScreen
import earth.health.ui.meal.MealScreen
import earth.health.ui.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val days = viewModel<DayViewModel>().days
    val mealViewModel = viewModel<MealViewModel>()
    val mealWithFoodsList = mealViewModel.mealWithFoodsList
    val foodViewModel = viewModel<FoodViewModel>()
    if (mealWithFoodsList.isEmpty()) { // This is usefull for the really first launch to do not have meals blank page
        mealViewModel.reloadAll()
    }
    val mealFoodCrossRefViewModel = viewModel<MealFoodCrossRefViewModel>()

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            HomeScreen(navController = navController, days = days)
        }
        composable(Destination.MEALS.link) {
            MealHomeScreen(mealsWithFoods = mealWithFoodsList) { meal ->
                navController.navigate(Destination.MEALS.link + "/${meal.id}")
            }
        }
        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            val mealId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            MealScreen(
                mealWithFoods = mealWithFoodsList.first() { it.meal.id == mealId },
                addAction = { navController.navigate(Destination.FOODS.link + "/meal/${mealId}") },
                textAction = { navController.navigate(Destination.FOODS.link + "/${it.id}") }
            )
        }
        composable(Destination.MEALS.link + "/{meal_id}/{food_id}") {
            val mealId = it.arguments!!.getString("meal_id")!!.toLong()
            val foodId = it.arguments!!.getString("food_id")!!.toLong()
            val food = foodViewModel.foodList.first { it.id == foodId}
            val meal = mealViewModel.mealList.first { it.id == mealId}
            var quantity by rememberSaveable { mealFoodCrossRefViewModel.getQuantity(meal = meal, food = food) }
            AddSelectedFoodToMeal(
                food = food,
                meal = meal,
                quantity = quantity,
                onChangeQuantity = { quantity = it },
                addAction = {
                    navController.popBackStack()
                    navController.popBackStack() //back to the meal screen
                    suspend { mealFoodCrossRefViewModel.insert(meal, food, quantity.toDouble()) }
                    mealViewModel.reloadMeal(meal = meal)
                }
            )
        }
        composable(Destination.FOODS.link + "/meal/{mealId}") {
            val mealId = it.arguments!!.getString("mealId")!!.toLong()
            if (mealId > 0) {
                AllFoodsScreen(
                    actionOpenFood = { food ->
                        navController.navigate(Destination.MEALS.link + "/${mealId}/${food.id}")
                    }
                )
            } else {
                AllFoodsScreen(
                    actionOpenFood = { food ->
                        navController.navigate(Destination.FOODS.link + "/${food.id}")
                    }
                )
            }
        }
        composable(Destination.FOODS.link + "/{id}") { navBackStackEntry ->
            val foodId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            FoodScreen(food = foodViewModel.foodWithMealsList.first() { it.food.id == foodId })
        }
        composable(Destination.WEIGHT.link) {
            WeightHomeScreen(navController)
        }
        composable(Destination.ADD_FOOD_SCREEN.link) {
            AddFoodScreen()
        }
    }
}