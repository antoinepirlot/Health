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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.MealViewModel
import earth.health.router.Destination
import earth.health.ui.components.Card

@Composable
fun MealHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mealViewModel: MealViewModel,
) {
    val mealWithFoodsList = mealViewModel.mealWithFoodsList
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (mealWithFoods in mealWithFoodsList) {
            val meal = mealWithFoods.meal
            Card(
                title = stringResource(id = meal.name.mealNameId),
                text = "${meal.totalKcal} kcal",
                mainAction = {
                    navController.navigate(Destination.MEALS.link + "/${meal.id}")
                    mealViewModel.selectMeal(mealWithFoods)
                },
                //TODO
                fastAction = {}
            )
        }
    }
}

@Preview
@Composable
fun MealHomeScreenPreview() {
    MealHomeScreen(mealViewModel = viewModel(), navController = rememberNavController())
}