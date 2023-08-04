package earth.health.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import earth.health.R
import earth.health.data.entity.Day
import earth.health.ui.theme.HealthTheme
import earth.health.router.Destination

@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, days: List<Day>) {
    if (days.isNotEmpty()) {
        val latestDay = days.last()
        val kcalText = latestDay.totalKcal.toString() + "/1920kcal"
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                title = stringResource(id = R.string.food),
                text = kcalText,
                mainAction = { navController.navigate(Destination.MEALS.link) },
                fastAction = {})
            Card(
                title = stringResource(id = R.string.weight),
                text = "80 kg",
                mainAction = { navController.navigate(Destination.WEIGHT.link) },
                fastAction = {})
            Card(
                title = stringResource(id = R.string.add_food_screen),
                text = "",
                mainAction = { navController.navigate(Destination.ADD_FOOD_SCREEN.link) }
            ) {

            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HealthTheme {
        HomeScreen(navController = rememberNavController(), days = listOf())
    }
}