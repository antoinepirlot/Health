package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import earth.health.data.view_models.FoodViewModel

@Composable
fun FoodScreen(
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel,
    foodId: Long
) {
    val food = foodViewModel.readFood(foodId = foodId)
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = food.name)
    }
}