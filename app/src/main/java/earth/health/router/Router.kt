package earth.health.router

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
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
    val days = dayViewModel.days
    val mealViewModel = viewModel<MealViewModel>()
    val mealWithFoodsList = mealViewModel.mealWithFoodsList
    val mealList = mealViewModel.mealList
    val foodViewModel = viewModel<FoodViewModel>()
    val foodWithMealsList = foodViewModel.foodWithMealsList
    val foodList = foodViewModel.foodList
    val mealFoodCrossRefViewModel = viewModel<MealFoodCrossRefViewModel>()

    NavHost(navController = navController, startDestination = Destination.HOME.link) {
        composable(Destination.HOME.link) {
            /**
             * HOME PAGE
             */
            if (days.isEmpty()) {
                InitialiseHomeScreen(startNewDay = { dayViewModel.startNewDay() })
            } else {
                HomeScreen(navController = navController, days = days)
            }
        }
        composable(Destination.MEALS.link) {
            /**
             * All Meals Screens
             */
            MealHomeScreen(mealsWithFoods = mealWithFoodsList) { meal ->
                navController.navigate(Destination.MEALS.link + "/${meal.id}")
            }
        }
        composable(Destination.MEALS.link + "/{id}") { navBackStackEntry ->
            /**
             * 1 Meal Screen
             */
            val mealId = navBackStackEntry.arguments!!.getString("id")!!.toLong()
            MealScreen(
                mealWithFoods = mealWithFoodsList.first { mealWithFoods ->
                    mealWithFoods.meal.id == mealId
                },
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
            val food = foodList.first { food: Food -> food.id == foodId }
            val meal = mealList.first { meal: Meal -> meal.id == mealId }
            var quantity by rememberSaveable {
                mealFoodCrossRefViewModel.getQuantity(
                    meal = meal,
                    food = food
                )
            }
            AddSelectedFoodToMealScreen(
                food = food,
                meal = meal,
                quantity = quantity,
                onChangeQuantity = {quantity = it },
                addAction = {
                    navController.popBackStack()
                    navController.popBackStack() //back to the meal screen
                    mealFoodCrossRefViewModel.insert(meal, food, quantity.toDouble())
                    // TODO fix to update the right kcal number (and not adding indefinitly
                    mealViewModel.updateKcal(meal, food, quantity)
                    dayViewModel.updateKcal(meal, food, quantity)
                    mealViewModel.reloadMeal(meal = meal)
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
                    actionOpenFood = { food ->
                        navController.navigate(Destination.MEALS.link + "/${mealId}/${food.id}")
                    }
                )
            } else {
                AllFoodsScreen(
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
            FoodScreen(food = foodViewModel.foodWithMealsList.first { it.food.id == foodId })
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