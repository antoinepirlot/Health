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

package earth.health.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.entity.Food
import earth.health.data.view_models.FoodViewModel

/**
 * @author Antoine Pirlot on 02/08/2023
 */

@Composable
fun AddFoodForm(
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel
) {
    var foodName by rememberSaveable { mutableStateOf("") }
    var kcal by rememberSaveable { mutableStateOf("0") }
    var lipids by rememberSaveable { mutableStateOf("0.0") }
    var saturatedLipids by rememberSaveable { mutableStateOf("0.0") }
    var carbohydates by rememberSaveable { mutableStateOf("0.0") }
    var sugar by rememberSaveable { mutableStateOf("0.0") }
    var protein by rememberSaveable { mutableStateOf("0.0") }
    var alimentaryFiber by rememberSaveable { mutableStateOf("0.0") }
    var calcium by rememberSaveable { mutableStateOf("0.0") }

    val toast = Toast.makeText(
        LocalContext.current,
        stringResource(id = R.string.add_food_succeed),
        Toast.LENGTH_SHORT
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        // todo try to simplify
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.food_name_form)) },
            value = foodName,
            onValueChange = { newFoodName -> foodName = newFoodName },
        )
        OutlinedTextField(
            label = { Text(text = "kcal") },
            value = kcal,
            onValueChange = { newFoodKcal -> kcal = newFoodKcal }
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.lipids)) },
            value = lipids,
            onValueChange = { newFoodLipids -> lipids = newFoodLipids }
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.saturated_lipids)) },
            value = saturatedLipids,
            onValueChange = { newFoodSaturatedLipids ->
                saturatedLipids = newFoodSaturatedLipids
            }
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.carbohydrates)) },
            value = carbohydates,
            onValueChange = { newFoodCarbohydrates ->
                carbohydates = newFoodCarbohydrates
            }
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.sugar)) },
            value = sugar,
            onValueChange = { newFoodSugar -> sugar = newFoodSugar }
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.protein)) },
            value = protein,
            onValueChange = { newFoodProtein -> protein = newFoodProtein }
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.alimentary_fiber)) },
            value = alimentaryFiber,
            onValueChange = { newFoodAlimentaryFiber ->
                alimentaryFiber = newFoodAlimentaryFiber
            }
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.calcium)) },
            value = calcium,
            onValueChange = { newFoodCalcium -> calcium = newFoodCalcium }
        )
        Button(
            onClick = {
                val food = Food(
                    name = foodName,
                    kcal = kcal.toInt(),
                    lipids = lipids.toDouble(),
                    saturatedLipids = saturatedLipids.toDouble(),
                    carbohydrates = carbohydates.toDouble(),
                    sugar = sugar.toDouble(),
                    protein = protein.toDouble(),
                    alimentaryFiber = alimentaryFiber.toDouble(),
                    calcium = calcium.toDouble()
                )
                foodViewModel.upsert(food)
                foodName = ""
                kcal = "0"
                lipids = "0.0"
                saturatedLipids = "0.0"
                carbohydates = "0.0"
                sugar = "0.0"
                protein = "0.0"
                alimentaryFiber = "0.0"
                calcium = "0.0"
                toast.show()
            }
        ) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Preview
@Composable
fun AddFoodFormPreview() {
    AddFoodForm(foodViewModel = viewModel())
}