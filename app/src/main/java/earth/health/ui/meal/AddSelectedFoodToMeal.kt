package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals

@Composable
fun AddSelectedFoodToMeal(modifier: Modifier = Modifier, food: Food, meal: Meal, quantity: String, onChangeQuantity: (String) -> Unit, addAction: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(text = stringResource(id = R.string.add_selected_food_to_meal_screen))
        TextField(value = quantity, onValueChange = { onChangeQuantity(it) })
        Button(onClick = addAction) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Preview
@Composable
fun AddSelectedFoodToMealPreview() {
    AddSelectedFoodToMeal(food = Food(name = "Hello"), meal = Meal(name = Meals.BREAKFAST, dayId = 0), quantity = "0.0", onChangeQuantity = {}, addAction = {})
}