package earth.health.router

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealWithFoodsViewModel
import earth.health.ui.HomeScreen
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
            HomeScreen(navController = navController, dayViewModel = dayViewModel)
        }

        composable(Destination.MEALS.link) {
            /**
             * All Meals Screens
             */
            if(mealWithFoodsViewModel.isEmpty()) {
                mealWithFoodsViewModel.reloadAll()
            }
            MealHomeScreen(
                mealWithFoodsViewModel = mealWithFoodsViewModel,
                navController = navController
            )
        }

        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            /**
             * 1 Meal Screen
             */
            val mealId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            MealScreen(
                mealId = mealId,
                navController = navController,
                mealWithFoodsViewModel = mealWithFoodsViewModel,
                mealFoodCrossRefViewModel = mealFoodCrossRefViewModel,
                day = dayViewModel.getLastDay()
            )
        }
        composable(Destination.MEALS.link + "/{meal_id}/{food_id}") { navBackStackEntry ->
            /**
             * ADD FOOD TO MEAL
             */
            val mealId = navBackStackEntry.arguments!!.getString("meal_id")!!.toLong()
            val foodId = navBackStackEntry.arguments!!.getString("food_id")!!.toLong()
            AddSelectedFoodToMealScreen(
                navController = navController,
                foodId = foodId,
                mealId = mealId,
                foodViewModel = foodViewModel,
                mealWithFoodsViewModel = mealWithFoodsViewModel,
                mealFoodCrossRefViewModel = mealFoodCrossRefViewModel,
                dayViewModel = dayViewModel
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
                    },
                    mealWithFoodsViewModel = mealWithFoodsViewModel
                )
            } else {
                AllFoodsScreen(
                    foodViewModel = foodViewModel,
                    actionOpenFood = {food ->
                        navController.navigate(Destination.FOODS.link + "/${food.id}")
                    },
                    mealWithFoodsViewModel = mealWithFoodsViewModel
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