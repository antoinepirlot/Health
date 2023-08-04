package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import earth.health.data.entity.relations.FoodWithMeals

@Composable
fun FoodScreen(food: FoodWithMeals) {
    Column {
        Text(text = food.food.name)
    }
}