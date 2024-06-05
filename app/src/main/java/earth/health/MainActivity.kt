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

package earth.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.router.Router
import earth.health.ui.theme.HealthTheme
import earth.health.ui.components.HealthTopAppBar

/**
 * @author Antoine Pirlot on 22/07/2023
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {
                val modifier = Modifier
                val navController = rememberNavController()

                val dayViewModel = viewModel<DayViewModel>()
                val mealViewModel = viewModel<MealViewModel>()
                val mealFoodCrossRefViewModel = viewModel<MealFoodCrossRefViewModel>()
                val foodViewModel = viewModel<FoodViewModel>()

                Surface(modifier = modifier.fillMaxSize()) {
                    Scaffold (
                        modifier = modifier,
                        topBar = {
                            HealthTopAppBar(
                                modifier = modifier,
                                navController = navController,
                            )
                        },
                    ) { innerPadding ->
                        Router(
                            modifier = modifier.padding(innerPadding),
                            navController = navController,
                            dayViewModel = dayViewModel,
                            mealViewModel = mealViewModel,
                            mealFoodCrossRefViewModel = mealFoodCrossRefViewModel,
                            foodViewModel = foodViewModel,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ComponentActivityPreview() {
    ComponentActivity()
}