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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import earth.health.R
import earth.health.data.view_models.FoodViewModel

@Composable
fun FoodScreen(
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel,
    foodId: Long
) {
    val food = foodViewModel.readFood(foodId)
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = food.name)
        Text(text = "${food.kcal} kcal")
        Text(text = stringResource(id = R.string.carbohydrates) + ": ${food.carbohydrates} g")
        Text(text = stringResource(id = R.string.sugar) + ": ${food.sugar} g")
        Text(text = stringResource(id = R.string.lipids) + ": ${food.lipids} g")
        Text(text = stringResource(id = R.string.saturated_lipids) + ": ${food.saturatedLipids} g")
        Text(text = stringResource(id = R.string.protein) + ": ${food.protein} g")
        Text(text = stringResource(id = R.string.alimentary_fiber) + ": ${food.alimentaryFiber} g")
        Text(text = stringResource(id = R.string.calcium) + ": ${food.calcium} g")
    }
}