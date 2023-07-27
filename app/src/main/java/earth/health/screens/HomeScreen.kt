package earth.health.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import earth.health.R
import earth.health.data.entity.Day
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.ui.theme.HealthTheme
import earth.health.router.Destination

@Composable
fun HomeScreen(navController: NavController, dayViewModel: DayViewModel) {
    println(message = "EMPTY WTF: -->" + dayViewModel.days.isEmpty())
    val latestDay = Day()// dayViewModel.days.last()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val kcalText = latestDay.totalKcal.toString() + "/1920kcal"
        Card(title = stringResource(id = R.string.food), text = kcalText, mainAction = {navController.navigate(Destination.MEALS.link)}, fastAction = {})
        Card(title = stringResource(id = R.string.weight), text = "80 kg", mainAction = {navController.navigate(Destination.WEIGHT.link)}, fastAction = {})
    }
}

@Preview()
@Composable
fun HomePreview() {
    HealthTheme {
        HomeScreen(rememberNavController(), viewModel())
    }
}