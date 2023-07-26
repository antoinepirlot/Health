package earth.health.screens.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.R
import earth.health.data.entity.Meal
import earth.health.data.view_models.MealViewModel
import earth.health.screens.Card

@Composable
fun MealHomeScreen(meals: List<Meal>, mealViewModel: MealViewModel, action: (Meal) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        for (meal in meals) {
            Card(
                title = stringResource(id = R.string.breakfast),
                text = "null",
                mainAction = { action(meal) },
                fastAction = {}
            )
            Card(
                title = stringResource(id = R.string.lunch),
                text = "null",
                mainAction = {},
                fastAction = {})
            Card(
                title = stringResource(id = R.string.dinner),
                text = "null",
                mainAction = {},
                fastAction = {})
            Card(
                title = stringResource(id = R.string.extras),
                text = "null",
                mainAction = {},
                fastAction = {})
        }
    }
}

@Preview
@Composable
fun MealHomeScreenPreview() {
    MealHomeScreen(listOf(), mealViewModel = viewModel()) {}
}