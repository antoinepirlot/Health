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