package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.entity.Food
import earth.health.data.view_models.FoodViewModel

@Composable
fun AllFoodsScreen(modifier: Modifier = Modifier, actionOpenFood: (Food) -> Unit) {
    val foodList = viewModel<FoodViewModel>().foodList
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (foodList.isEmpty()) {
            Text(text = stringResource(id = R.string.nothing))
        } else {
            Text(text = stringResource(id = R.string.food_list))
            FoodListScreen(
                foodList = foodList,
                actionClickOnFood = actionOpenFood
            )
        }
    }
}

@Preview
@Composable
fun AllFoodScreenPreview() {
    AllFoodsScreen(actionOpenFood = {})
}