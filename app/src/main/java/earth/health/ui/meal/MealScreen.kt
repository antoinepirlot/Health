package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.data.entity.Meal
import earth.health.data.entity.getDefaultMeals

@Composable
fun MealScreen(meal: Meal) {
    Column {
        Text(text = stringResource(id = meal.name.mealNameId))
//        for (food in meal.foodList) {
//            TextButton(onClick = { /*TODO*/ }) {
//                //fillMaxWidth makes the button clickable on all the width even on blank
//                Text(text = food.name, modifier = Modifier.fillMaxWidth())
//            }
//            Divider()
//        }
    }
}

@Preview
@Composable
fun MealScreenPreview() {
    MealScreen(meal = getDefaultMeals()[0])
}