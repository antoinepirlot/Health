package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.data.entity.Meal
import earth.health.ui.Card

@Composable
fun MealHomeScreen(meals: List<Meal>, action: (Meal) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        for (meal in meals) {
            Card(
                title = stringResource(id = meal.name.mealNameId),
                text = "${meal.totalKcal} kcal",
                mainAction = { action(meal) },
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