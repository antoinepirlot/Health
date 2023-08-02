package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R

@Composable
fun AddFoodForm(
    modifier: Modifier = Modifier,
    name: String,
    onChangeName: (String) -> Unit,
    kcal: String,
    onChangeKcal: (String) -> Unit,
    lipids: String,
    onChangeLipids: (String) -> Unit,
    saturatedLipids: String,
    onChangeSaturatedLipids: (String) -> Unit,
    carbohydrates: String,
    onChangeCarbohydrates: (String) -> Unit,
    sugar: String,
    onChangeSugar: (String) -> Unit,
    protein: String,
    onChangeProtein: (String) -> Unit,
    alimentaryFiber: String,
    onChangeAlimentaryFiber: (String) -> Unit,
    calcium: String,
    onChangeCalcium: (String) -> Unit,
) {
    Column(modifier = modifier) {
        // todo try to simplify
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.food_name_form)) },
            value = name,
            onValueChange = onChangeName
        )
        OutlinedTextField(
            label = { Text(text = "kcal") },
            value = kcal,
            onValueChange = onChangeKcal
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.lipids)) },
            value = lipids,
            onValueChange = onChangeLipids
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.saturated_lipids)) },
            value = saturatedLipids,
            onValueChange = onChangeSaturatedLipids
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.carbohydrates)) },
            value = carbohydrates,
            onValueChange = onChangeCarbohydrates
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.sugar)) },
            value = sugar,
            onValueChange = onChangeSugar
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.protein)) },
            value = protein,
            onValueChange = onChangeProtein
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.alimentary_fiber)) },
            value = alimentaryFiber,
            onValueChange = onChangeAlimentaryFiber
        )
        OutlinedTextField(
            label = { Text(text = stringResource(id = R.string.calcium)) },
            value = calcium,
            onValueChange = onChangeCalcium
        )
    }
}

@Preview
@Composable
fun AddFoodFormPreview() {
    AddFoodForm(
        name = "",
        onChangeName = {},
        kcal = "",
        onChangeKcal = {},
        lipids = "",
        onChangeLipids = {},
        saturatedLipids = "",
        onChangeSaturatedLipids = {},
        carbohydrates = "",
        onChangeCarbohydrates = {},
        sugar = "",
        onChangeSugar = {},
        protein = "",
        onChangeProtein = {},
        alimentaryFiber = "",
        onChangeAlimentaryFiber = {},
        calcium = "",
        onChangeCalcium = {} 
    )
}