package earth.health.ui.food

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R
import earth.health.data.entity.Food
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealWithFoodsViewModel

@Composable
fun AllFoodsScreen(
    modifier: Modifier = Modifier,
    foodViewModel: FoodViewModel,
    foodList: List<Food> = foodViewModel.foodList,
    actionOpenFood: (Food) -> Unit,
    mealWithFoodsViewModel: MealWithFoodsViewModel
) {
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
                actionClickOnFood = actionOpenFood,
                actionDeleteFood = { food ->
                    mealWithFoodsViewModel.foodIsUsed(food = food)
                }
            )
        }
    }
}

@Preview
@Composable
fun AllFoodScreenPreview() {
    AllFoodsScreen(
        foodViewModel = FoodViewModel(Application()),
        actionOpenFood = {},
        mealWithFoodsViewModel = MealWithFoodsViewModel(application = Application())
    )
}