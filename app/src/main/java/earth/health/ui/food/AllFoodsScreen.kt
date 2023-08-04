package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R
import earth.health.data.entity.relations.FoodWithMeals

@Composable
fun AllFoodsScreen(modifier: Modifier = Modifier, foodList: List<FoodWithMeals>, actionOpenFood: (FoodWithMeals) -> Unit) {
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
    AllFoodsScreen(foodList = listOf(), actionOpenFood = {})
}