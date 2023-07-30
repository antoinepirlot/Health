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
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.entity.Meal
import earth.health.data.entity.getDefaultMeals

@Composable
fun MealScreen(meal: Meal) {
    Column {
        Text(text = stringResource(id = meal.name.mealNameId))
//        val foodListOfMeal = viewModel<MealFoodRelViewModel>().getAllFoodOf(meal)
//        if (foodListOfMeal.isEmpty()) {
//            Text(text = stringResource(id = R.string.nothing))
//        } else {
//            for (food in foodListOfMeal) {
//                TextButton(onClick = { /*TODO*/ }) {
//                    //fillMaxWidth makes the button clickable on all the width even on blank
//                    Text(text = food.name, modifier = Modifier.fillMaxWidth())
//                }
//                Divider()
//            }
//        }
    }
}

@Preview
@Composable
fun MealScreenPreview() {
    MealScreen(meal = getDefaultMeals()[0])
}