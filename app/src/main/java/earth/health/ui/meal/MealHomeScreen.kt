package earth.health.ui.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.MealViewModel
import earth.health.router.Destination
import earth.health.ui.components.Card

@Composable
fun MealHomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    mealViewModel: MealViewModel,
) {
    val mealWithFoodsList = mealViewModel.mealWithFoodsList
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        for (mealWithFoods in mealWithFoodsList) {
            val meal = mealWithFoods.meal
            Card(
                title = stringResource(id = meal.name.mealNameId),
                text = "${meal.totalKcal} kcal",
                mainAction = {
                    navController.navigate(Destination.MEALS.link + "/${meal.id}")
                },
                //TODO
                fastAction = {}
            )
        }
    }
}

@Preview
@Composable
fun MealHomeScreenPreview() {
    MealHomeScreen(mealViewModel = viewModel(), navController = rememberNavController())
}