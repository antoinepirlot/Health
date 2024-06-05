/*
 * This file is part of Health.
 *
 *  Health is free software: you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software Foundation,
 *  either version 3 of the License, or (at your option) any later version.
 *
 *  Health is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with Health.
 *  If not, see <https://www.gnu.org/licenses/>.
 *
 *  **** INFORMATIONS ABOUT THE AUTHOR *****
 *  The author of this file is Antoine Pirlot, the owner of this project.
 *  You find this original project on github.
 *
 *  My github link is: https://github.com/antoinepirlot
 *  This current project's link is: https://github.com/antoinepirlot/Health
 *
 *  You can contact me via my email: pirlot.antoine@outlook.com
 *  PS: I don't answer quickly.
 */

package earth.health.ui.meal

import android.app.Application
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import earth.health.R
import earth.health.data.entity.Day
import earth.health.data.entity.getBlankDay
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.router.Destination
import earth.health.ui.food.FoodListScreen

/**
 * @author Antoine Pirlot on 24/07/2023
 */

@Composable
fun MealScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mealId: Long,
    mealViewModel: MealViewModel,
    mealFoodCrossRefViewModel: MealFoodCrossRefViewModel,
    day: Day
) {
    val mealWithFoods = mealViewModel.readMealWithFoods(mealId = mealId)
    val selectedMeal by remember { mealViewModel.selectedMeal }
    val selectedMealFoodList = mealViewModel.selectedMealFoodList

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = selectedMeal!!.name.mealNameId))
        if (selectedMealFoodList.isEmpty()) {
            Text(text = stringResource(id = R.string.nothing))
        } else {
            val toast = Toast.makeText(
                LocalContext.current,
                stringResource(id = R.string.remove_food_from_meal_succeed),
                Toast.LENGTH_LONG
            )
            FoodListScreen(
                foodList = selectedMealFoodList,
                actionClickOnFood = { food ->
                    navController.navigate(Destination.FOODS.link + "/${food.id}")
                },
                actionDeleteFood = { food ->
                    mealFoodCrossRefViewModel
                        .remove(mealWithFoods = mealWithFoods, food = food, day = day)
                    selectedMealFoodList.remove(food)
                    toast.show()
                })
        }
        Button(onClick = {
            navController.navigate(Destination.FOODS.link + "/meal/${mealId}")
        }) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Preview
@Composable
fun MealScreenWithoutFoodPreview() {
    MealScreen(
        navController = rememberNavController(),
        mealId = 0,
        mealViewModel = viewModel(),
        mealFoodCrossRefViewModel = MealFoodCrossRefViewModel(Application()),
        day = getBlankDay())
}