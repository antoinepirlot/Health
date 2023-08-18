package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.view_models.FoodViewModel
import earth.health.ui.utils.AddFoodForm

@Composable
fun AddFoodScreen(
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(text = stringResource(id = R.string.add_food_screen))
        AddFoodForm(foodViewModel = foodViewModel)
    }
}

@Preview
@Composable
fun AddFoodScreenPreview() {
    AddFoodScreen(foodViewModel = viewModel())
}