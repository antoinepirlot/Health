package earth.health.router

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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

@Composable
fun Router(
    modifier: Modifier = Modifier,
    dayViewModel: DayViewModel,
    mealViewModel: MealViewModel,
    mealFoodCrossRefViewModel: MealFoodCrossRefViewModel,
    foodViewModel: FoodViewModel,
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            /**
             * HOME PAGE
             */
            HomeScreen(
                modifier = modifier,
                navController = navController,
                dayViewModel = dayViewModel
            )
        }

        composable(Destination.MEALS.link) {
            /**
             * All Meals Screens
             */
            // TODO find a fix
            if(mealViewModel.isEmpty()) {
                // Needed for the very first launch to show meals
                mealViewModel.reloadAll()
            }
            MealHomeScreen(
                modifier = modifier,
                navController = navController,
                mealViewModel = mealViewModel,
            )
        }

        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            /**
             * 1 Meal Screen
             */
            val mealId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            MealScreen(
                modifier = modifier,
                navController = navController,
                mealId = mealId,
                mealViewModel = mealViewModel,
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
                modifier = modifier,
                navController = navController,
                foodId = foodId,
                mealId = mealId,
                foodViewModel = foodViewModel,
                mealViewModel = mealViewModel,
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
                modifier = modifier,
                navController = navController,
                mealId = mealId,
                foodViewModel = foodViewModel,
                mealViewModel = mealViewModel
            )
        }

        composable(Destination.FOODS.link + "/{id}") { navBackStackEntry ->
            /**
             * 1 FOOD SCREEN
             */
            val foodId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            FoodScreen(
                modifier = modifier,
                foodViewModel = foodViewModel,
                foodId = foodId
            )
        }

        composable(Destination.WEIGHT.link) {
            /**
             * WEIGHT SCREEN
             */
            WeightHomeScreen(
                modifier = modifier,
                navController
            )
        }

        composable(Destination.ADD_FOOD_SCREEN.link) { navBackStackEntry ->
            /**
             * ADD FOOD SCREEN
             */
            AddFoodScreen(
                modifier = modifier,
                foodViewModel = foodViewModel
            )
        }
    }
}