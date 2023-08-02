package earth.health.ui.food

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
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

@Composable
fun AddFoodScreen(
    modifier: Modifier = Modifier
) {
    val foodViewModel = viewModel<FoodViewModel>()
    var foodName by rememberSaveable { mutableStateOf("") }
    var kcal by rememberSaveable { mutableStateOf("0") }
    var lipids by rememberSaveable { mutableStateOf("0.0") }
    var saturatedLipids by rememberSaveable { mutableStateOf("0.0") }
    var carbohydates by rememberSaveable { mutableStateOf("0.0") }
    var sugar by rememberSaveable { mutableStateOf("0.0") }
    var protein by rememberSaveable { mutableStateOf("0.0") }
    var alimentaryFiber by rememberSaveable { mutableStateOf("0.0") }
    var calcium by rememberSaveable { mutableStateOf("0.0") }

    Column(modifier = modifier) {
        val toast = Toast.makeText(
            LocalContext.current,
            stringResource(id = R.string.add_food_succeed),
            Toast.LENGTH_SHORT
        )
        Text(text = stringResource(id = R.string.add_food_screen))
        Row(modifier = modifier) {
            AddFoodForm(
                name = foodName,
                onChangeName = { newFoodName -> foodName = newFoodName },
                kcal = kcal,
                onChangeKcal = { newFoodKcal -> kcal = newFoodKcal },
                lipids = lipids,
                onChangeLipids = { newFoodLipids -> lipids = newFoodLipids },
                saturatedLipids = saturatedLipids,
                onChangeSaturatedLipids = { newFoodSaturatedLipids ->
                    saturatedLipids = newFoodSaturatedLipids
                },
                carbohydrates = carbohydates,
                onChangeCarbohydrates = { newFoodCarbohydrates ->
                    carbohydates = newFoodCarbohydrates
                },
                sugar = sugar,
                onChangeSugar = { newFoodSugar -> sugar = newFoodSugar },
                protein = protein,
                onChangeProtein = { newFoodProtein -> protein = newFoodProtein },
                alimentaryFiber = alimentaryFiber,
                onChangeAlimentaryFiber = { newFoodAlimentaryFiber ->
                    alimentaryFiber = newFoodAlimentaryFiber
                },
                calcium = calcium,
                onChangeCalcium = { newFoodCalcium -> calcium = newFoodCalcium }
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
                    foodViewModel.createFood(food)
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
}

@Preview
@Composable
fun AddFoodScreenPreview() {
    AddFoodScreen(Modifier)
}