package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import earth.health.data.entity.relations.FoodWithMeals

@Composable
fun FoodScreen(modifier: Modifier = Modifier, food: FoodWithMeals) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = food.food.name)
    }
}