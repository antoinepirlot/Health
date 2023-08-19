package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealViewModel

@Composable
fun AddSelectedFoodToMealScreen(
    modifier: Modifier = Modifier,
    dayViewModel: DayViewModel,
    foodViewModel: FoodViewModel,
    mealViewModel: MealViewModel,
    mealFoodCrossRefViewModel: MealFoodCrossRefViewModel,
    foodId: Long,
    mealId: Long,
    addAction: () -> Unit
) {
    val food by remember {
        foodViewModel.readFood(foodId = foodId)
    }
    val mealWithFoods = mealViewModel.readMealWithFoods(mealId = mealId)
    var quantity by rememberSaveable {
        mealFoodCrossRefViewModel.getQuantity(
            meal = mealWithFoods.meal,
            food = food
        )
    }
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Text(text = stringResource(id = R.string.add_selected_food_to_meal_screen))
        TextField(value = quantity, onValueChange = { quantity = it })
        Button(onClick = {
            addAction()
            val mealDay = dayViewModel.getOne(meal = mealWithFoods.meal)
            mealFoodCrossRefViewModel.insert(
                mealWithFoods = mealWithFoods,
                day = mealDay,
                food = food,
                quantity = quantity.toDouble()
            )
        }) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Preview
@Composable
fun AddSelectedFoodToMealPreview() {
    AddSelectedFoodToMealScreen(
        foodId = 1,
        mealId = 1,
        mealViewModel = viewModel(),
        foodViewModel = viewModel(),
        mealFoodCrossRefViewModel = viewModel(),
        dayViewModel = viewModel(),
        addAction = {})
}