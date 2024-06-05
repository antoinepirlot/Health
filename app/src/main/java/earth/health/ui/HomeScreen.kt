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

package earth.health.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import earth.health.R
import earth.health.data.view_models.DayViewModel
import earth.health.ui.theme.HealthTheme
import earth.health.router.Destination
import earth.health.ui.components.Card

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    dayViewModel: DayViewModel
) {
    val isLoaded by rememberSaveable {
        dayViewModel.isLoaded
    }
    if (!isLoaded)
        InitialiseHomeScreen {}
    else {
        val latestDay = dayViewModel.getLastDay()
        val kcalText = latestDay.totalKcal.toString() + "/1920kcal"
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                title = stringResource(id = R.string.food),
                text = kcalText,
                mainAction = { navController.navigate(Destination.MEALS.link) },
                fastAction = {})
            Card(
                title = stringResource(id = R.string.weight),
                text = "80 kg",
                mainAction = { navController.navigate(Destination.WEIGHT.link) },
                fastAction = {})
            Card(
                title = stringResource(id = R.string.add_food_screen),
                text = "",
                mainAction = { navController.navigate(Destination.ADD_FOOD_SCREEN.link) },
                fastAction = {}
            )
            Card(
                title = stringResource(id = R.string.export_data),
                text = "",
                mainAction = {
                    navController.navigate(Destination.EXPORT_DATA.link)
                },
                fastAction = {}
            )
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HealthTheme {
        HomeScreen(navController = rememberNavController(), dayViewModel = viewModel())
    }
}

@Composable
fun InitialiseHomeScreen(startNewDay: () -> Unit) {
    Button(onClick = startNewDay) {
        Text(text = stringResource(id = R.string.initialise_message))
    }
}

@Preview
@Composable
fun InitialiseHomeScreenPreview() {
    InitialiseHomeScreen {}
}