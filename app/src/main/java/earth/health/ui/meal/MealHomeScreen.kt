package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods
import earth.health.ui.utils.Card

@Composable
fun MealHomeScreen(modifier: Modifier = Modifier, mealsWithFoods: List<MealWithFoods>, action: (Meal) -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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
    MealHomeScreen(mealsWithFoods = listOf(), action = {})
}