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
import earth.health.R
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals
import earth.health.data.entity.relations.MealWithFoods
import earth.health.ui.food.FoodListScreen

@Composable
fun MealScreen(
    modifier: Modifier = Modifier,
    mealWithFoods: MealWithFoods,
    addAction: () -> Unit,
    textAction: (Food) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = mealWithFoods.meal.name.mealNameId))
        if (mealWithFoods.foods.isEmpty()) {
            Text(text = stringResource(id = R.string.nothing))
            Button(onClick = addAction) {
                Text(text = stringResource(id = R.string.add))
            }
        } else {
            FoodListScreen(foodList = mealWithFoods.foods, actionClickOnFood = textAction)
        }
    }
}

@Preview
@Composable
fun MealScreenWithoutFoodPreview() {
    MealScreen(
        mealWithFoods = MealWithFoods(Meal(name = Meals.BREAKFAST, dayId = 0), listOf()),
        addAction = {},
        textAction = {})
}

@Preview
@Composable
fun MealScreenWithFoodPreview() {
    MealScreen(
        mealWithFoods = MealWithFoods(Meal(name = Meals.BREAKFAST, dayId = 0), listOf(
            Food(name = "Banana")
        )),
        addAction = {},
        textAction = {})
}