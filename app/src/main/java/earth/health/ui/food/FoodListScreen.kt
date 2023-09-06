package earth.health.ui.food

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import earth.health.data.entity.Food

@Composable
fun FoodListScreen(modifier: Modifier = Modifier, foodList: List<Food>, actionClickOnFood: (Food) -> Unit, actionDeleteFood: (Food) -> Unit) {
    LazyColumn {
        items(foodList) { food ->
            Row {
                TextButton(onClick = { actionClickOnFood(food) }) {
                    Text(text = food.name)
                }
                IconButton(onClick = { actionDeleteFood(food) }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete Food"
                    )
                }
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
        actionClickOnFood = {},
        actionDeleteFood = {}
    )
}