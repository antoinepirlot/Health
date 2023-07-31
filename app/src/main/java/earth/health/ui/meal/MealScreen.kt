package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals
import earth.health.data.entity.getDefaultMeals
import earth.health.data.entity.relations.MealWithFoods

@Composable
fun MealScreen(mealWithFoods: MealWithFoods?) {
    Column {
//        Text(text = stringResource(id = mealWithFoods.meal.name.mealNameId))
        if (mealWithFoods == null) {
            Text(text = stringResource(id = R.string.nothing))
        } else {
            for (food in mealWithFoods.foods) {
                TextButton(onClick = { /*TODO*/ }) {
                    //fillMaxWidth makes the button clickable on all the width even on blank
                    Text(text = food.name, modifier = Modifier.fillMaxWidth())
                }
                Divider()
            }
        }
    }
}

@Preview
@Composable
fun MealScreenPreview() {
    MealScreen(mealWithFoods = MealWithFoods(Meal(name = Meals.BREAKFAST, dayId = 0), listOf()))
}