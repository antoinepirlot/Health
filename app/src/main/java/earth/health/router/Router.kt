package earth.health.router

import androidx.compose.runtime.Composable
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
fun Router(
    dayViewModel: DayViewModel,
    mealWithFoodsViewModel: MealWithFoodsViewModel,
    mealFoodCrossRefViewModel: MealFoodCrossRefViewModel,
    foodViewModel: FoodViewModel,
) {
    val navController = rememberNavController()

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
                // Needed for the very first launch to show meals
                mealWithFoodsViewModel.reloadAll()
            }
            MealHomeScreen(
                navController = navController,
                mealWithFoodsViewModel = mealWithFoodsViewModel,
            )
        }

        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            /**
             * 1 Meal Screen
             */
            val mealId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            MealScreen(
                navController = navController,
                mealId = mealId,
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
            AllFoodsScreen(
                navController = navController,
                mealId = mealId,
                foodViewModel = foodViewModel,
                mealWithFoodsViewModel = mealWithFoodsViewModel
            )
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