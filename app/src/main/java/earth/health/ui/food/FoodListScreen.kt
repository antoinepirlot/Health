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

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import earth.health.data.entity.Food

/**
 * @author Antoine Pirlot on 04/08/2023
 */

@Composable
fun FoodListScreen(modifier: Modifier = Modifier, foodList: List<Food>, actionClickOnFood: (Food) -> Unit, actionDeleteFood: (Food) -> Unit) {
    LazyColumn {
        items(foodList) { food ->
            Row {
                TextButton(onClick = { actionClickOnFood(food) }) {
                    Text(text = food.name)
                }
                IconButton(onClick = { actionDeleteFood(food) }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete Food"
                    )
                }
            }
            Divider()
        }
    }
}

@Preview
@Composable
fun FoodListScreenPreview() {
    FoodListScreen(
        foodList = listOf(Food(name = "Banana")),
        actionClickOnFood = {},
        actionDeleteFood = {}
    )
}