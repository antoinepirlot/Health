package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.entity.Food
import earth.health.data.view_models.MealWithFoodsViewModel
import earth.health.ui.food.FoodListScreen

@Composable
fun MealScreen(
    modifier: Modifier = Modifier,
    mealId: Long,
    mealWithFoodsViewModel: MealWithFoodsViewModel,
    addAction: () -> Unit,
    textAction: (Food) -> Unit
) {
    val mealWithFoods = mealWithFoodsViewModel.readMealWithFoods(mealId = mealId)
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = mealWithFoods.meal.name.mealNameId))
        if (mealWithFoods.foods.isEmpty()) {
            Text(text = stringResource(id = R.string.nothing))
        } else {
            FoodListScreen(foodList = mealWithFoods.foods, actionClickOnFood = textAction)
        }
        Button(onClick = addAction) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Preview
@Composable
fun MealScreenWithoutFoodPreview() {
    MealScreen(
        mealId = 0,
        mealWithFoodsViewModel = viewModel(),
        addAction = {},
        textAction = {})
}