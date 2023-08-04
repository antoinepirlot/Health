package earth.health.ui.food

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import earth.health.data.entity.Food
import earth.health.data.entity.relations.FoodWithMeals

@Composable
fun FoodListScreen(foodList: List<Food>, actionClickOnFood: (Food) -> Unit) {
    LazyColumn {
        items(foodList) { food ->
            TextButton(onClick = { actionClickOnFood(food) }) {
                Text(text = food.name)
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
        actionClickOnFood = {}
    )
}