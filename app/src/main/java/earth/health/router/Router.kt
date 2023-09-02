package earth.health.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealWithFoodsViewModel
import earth.health.ui.HomeScreen
import earth.health.ui.InitialiseHomeScreen
import earth.health.ui.food.AddFoodScreen
import earth.health.ui.food.AllFoodsScreen
import earth.health.ui.food.FoodScreen
import earth.health.ui.meal.AddSelectedFoodToMealScreen
import earth.health.ui.meal.MealHomeScreen
import earth.health.ui.meal.MealScreen
import earth.health.ui.weight.WeightHomeScreen

@Composable
fun Router() {
    val navController = rememberNavController()
    val dayViewModel = viewModel<DayViewModel>()
    val mealWithFoodsViewModel = viewModel<MealWithFoodsViewModel>()
    val mealFoodCrossRefViewModel = viewModel<MealFoodCrossRefViewModel>()
    val foodViewModel = viewModel<FoodViewModel>()

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            /**
             * HOME PAGE
             */
            val isLoaded by rememberSaveable {
                dayViewModel.isLoaded
            }
            if (isLoaded) {
                HomeScreen(navController = navController, dayViewModel = dayViewModel)
            } else {
                InitialiseHomeScreen {

                }
            }
        }
        composable(Destination.MEALS.link) {
            /**
             * All Meals Screens
             */
            MealHomeScreen(mealWithFoodsViewModel = mealWithFoodsViewModel) { meal ->
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
                mealWithFoodsViewModel = mealWithFoodsViewModel,
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
                mealWithFoodsViewModel = mealWithFoodsViewModel,
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
                    foodList = foodViewModel.foodList,
                    actionOpenFood = { food ->
                        navController.navigate(Destination.MEALS.link + "/${mealId}/${food.id}")
                    }
                )
            } else {
                AllFoodsScreen(
                    foodList = foodViewModel.foodList,
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