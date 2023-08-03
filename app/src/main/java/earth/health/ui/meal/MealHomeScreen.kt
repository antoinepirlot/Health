package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods
import earth.health.ui.Card

@Composable
fun MealHomeScreen(mealsWithFoods: List<MealWithFoods>, action: (Meal) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        for (mealWithFoods in mealsWithFoods) {
            Card(
                title = stringResource(id = mealWithFoods.meal.name.mealNameId),
                text = "${mealWithFoods.meal.totalKcal} kcal",
                mainAction = { action(mealWithFoods.meal) },
                fastAction = {}
            )
        }
    }
}

@Preview
@Composable
fun MealHomeScreenPreview() {
    MealHomeScreen(listOf()) {}
}