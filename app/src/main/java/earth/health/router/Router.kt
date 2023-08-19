package earth.health.router

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import earth.health.ui.meal.AddSelectedFoodToMealScreen
import earth.health.ui.meal.MealHomeScreen
import earth.health.ui.meal.MealScreen
import earth.health.ui.weight.WeightHomeScreen
import earth.health.ui.InitialiseHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val dayViewModel = viewModel<DayViewModel>()
    val mealViewModel = viewModel<MealViewModel>()
    val mealFoodCrossRefViewModel = viewModel<MealFoodCrossRefViewModel>()
    val foodViewModel = viewModel<FoodViewModel>()

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            /**
             * HOME PAGE
             */
            val isVeryFirstLaunch by remember {
                dayViewModel.isEmpty()
            }
            HomeScreen(navController = navController, isVeryFirstLaunch = isVeryFirstLaunch, dayViewModel = dayViewModel)
        }
        composable(Destination.MEALS.link) {
            /**
             * All Meals Screens
             */
            MealHomeScreen(mealViewModel = mealViewModel) { meal ->
                navController.navigate(Destination.MEALS.link + "/${meal.id}")
            }
        }
        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            /**
             * 1 Meal Screen
             */
            val mealId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            MealScreen(
                mealId = mealId,
                mealViewModel = mealViewModel,
                addAction = {
                    navController.navigate(Destination.FOODS.link + "/meal/${mealId}")
                },
                textAction = { food ->
                    navController.navigate(Destination.FOODS.link + "/${food.id}")
                }
            )
        }
        composable(Destination.MEALS.link + "/{meal_id}/{food_id}") { navBackStackEntry ->
            /**
             * ADD FOOD TO MEAL
             */
            val mealId = navBackStackEntry.arguments!!.getString("meal_id")!!.toLong()
            val foodId = navBackStackEntry.arguments!!.getString("food_id")!!.toLong()
            AddSelectedFoodToMealScreen(
                foodId = foodId,
                mealId = mealId,
                foodViewModel = foodViewModel,
                mealViewModel = mealViewModel,
                mealFoodCrossRefViewModel = mealFoodCrossRefViewModel,
                dayViewModel = dayViewModel,
                addAction = {
                    navController.popBackStack()
                    navController.popBackStack() //back to the meal screen
                }
            )
        }
        composable(Destination.FOODS.link + "/meal/{mealId}") { navBackStackEntry ->
            /**
             * LIST OF FOOD SCREEN
             */
            val mealId = navBackStackEntry.arguments!!.getString("mealId")!!.toLong()
            if (mealId > 0) { // It means adding food to meal
                AllFoodsScreen(
                    foodViewModel = foodViewModel,
                    actionOpenFood = { food ->
                        navController.navigate(Destination.MEALS.link + "/${mealId}/${food.id}")
                    }
                )
            } else {
                AllFoodsScreen(
                    foodViewModel = foodViewModel,
                    actionOpenFood = {food ->
                        navController.navigate(Destination.FOODS.link + "/${food.id}")
                    }
                )
            }
        }
        composable(Destination.FOODS.link + "/{id}") { navBackStackEntry ->
            /**
             * 1 FOOD SCREEN
             */
            val foodId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            FoodScreen(foodViewModel = foodViewModel, foodId = foodId)
        }
        composable(Destination.WEIGHT.link) {
            /**
             * WEIGHT SCREEN
             */
            WeightHomeScreen(navController)
        }
        composable(Destination.ADD_FOOD_SCREEN.link) { navBackStackEntry ->
            /**
             * ADD FOOD SCREEN
             */
            AddFoodScreen(foodViewModel = foodViewModel)
        }
    }
}