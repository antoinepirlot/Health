package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.entity.Meal
import earth.health.data.view_models.MealViewModel
import earth.health.ui.Card

@Composable
fun MealHomeScreen(meals: List<Meal>, mealViewModel: MealViewModel, action: (Meal) -> Unit) {
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
    MealHomeScreen(listOf(), mealViewModel = viewModel()) {}
}