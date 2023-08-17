package earth.health.ui.food

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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