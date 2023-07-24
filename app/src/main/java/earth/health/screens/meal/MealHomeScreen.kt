package earth.health.screens.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import earth.health.R
import earth.health.screens.Card

@Composable
fun MealHomeScreen(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(title = stringResource(id = R.string.breakfast), text = "null", mainAction = {}, fastAction = {})
        Card(title = stringResource(id = R.string.lunch), text = "null", mainAction = {}, fastAction = {})
        Card(title = stringResource(id = R.string.dinner), text = "null", mainAction = {}, fastAction = {})
        Card(title = stringResource(id = R.string.extras), text = "null", mainAction = {}, fastAction = {})
    }
}

@Preview
@Composable
fun MealHomeScreenPreview() {
    MealHomeScreen(navController = rememberNavController())
}