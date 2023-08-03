package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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

@Composable
fun MealScreen(
    modifier: Modifier = Modifier,
    mealWithFoods: MealWithFoods,
    addAction: () -> Unit,
    textAction: (Food) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = mealWithFoods.meal.name.mealNameId))
        if (mealWithFoods.foods.isEmpty()) {
            Text(text = stringResource(id = R.string.nothing))
            Button(onClick = addAction) {
                Text(text = stringResource(id = R.string.add))
            }
        } else {
            val meal = mealWithFoods.meal
            for (food in mealWithFoods.foods) {
                TextButton(onClick = { textAction(food) }) {
                    //fillMaxWidth makes the button clickable on all the width even on blank
                    Text(
                        text = stringResource(id = meal.name.mealNameId),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Divider()
            }
        }
    }
}

@Preview
@Composable
fun MealScreenPreview() {
    MealScreen(
        mealWithFoods = MealWithFoods(Meal(name = Meals.BREAKFAST, dayId = 0), listOf()),
        addAction = {},
        textAction = {})
}