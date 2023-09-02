package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.data.entity.Meal
import earth.health.data.view_models.MealViewModel
import earth.health.ui.utils.Card

@Composable
fun MealHomeScreen(modifier: Modifier = Modifier, mealViewModel: MealViewModel, goToMealAction: (Meal) -> Unit) {
    val mealWithFoodsList = mealViewModel.mealWithFoodsList
    if (mealWithFoodsList.isEmpty()) {
        //this condition is required for the very firt launch otherrwise this page is blank
        mealViewModel.reloadAll()
    }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (mealWithFoodsList in mealWithFoodsList) {
            val meal = mealWithFoodsList.meal
            Card(
                title = stringResource(id = meal.name.mealNameId),
                text = "${meal.totalKcal} kcal",
                mainAction = { goToMealAction(meal) },
                fastAction = {}
            )
        }
    }
}

@Preview
@Composable
fun MealHomeScreenPreview() {
    MealHomeScreen(mealViewModel = viewModel(), goToMealAction = {})
}