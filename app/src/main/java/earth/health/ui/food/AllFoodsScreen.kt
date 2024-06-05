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

package earth.health.ui.food

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import earth.health.R
import earth.health.data.entity.Food
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.router.Destination

@Composable
fun AllFoodsScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mealId: Long,
    foodViewModel: FoodViewModel,
    foodList: List<Food> = foodViewModel.foodList,
    mealViewModel: MealViewModel
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (foodList.isEmpty()) {
            Text(text = stringResource(id = R.string.nothing))
        } else {
            Text(text = stringResource(id = R.string.food_list))
            FoodListScreen(
                foodList = foodList,
                actionClickOnFood = { food ->
                    if (mealId > 0) { // It means adding food to meal
                        navController.navigate(Destination.MEALS.link + "/${mealId}/${food.id}")
                    } else {
                        navController.navigate(Destination.FOODS.link + "/${food.id}")
                    }
                },
                actionDeleteFood = { food ->
                    mealViewModel.foodIsUsed(food = food)
                }
            )
        }
    }
}

@Preview
@Composable
fun AllFoodScreenPreview() {
    AllFoodsScreen(
        navController = rememberNavController(),
        mealId = -1,
        foodViewModel = FoodViewModel(Application()),
        mealViewModel = MealViewModel(application = Application())
    )
}