package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import earth.health.data.view_models.FoodViewModel

@Composable
fun FoodScreen(
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel,
    foodId: Long
) {
    val food by remember {
        foodViewModel.readFood(foodId = foodId)
    }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = food.name)
    }
}