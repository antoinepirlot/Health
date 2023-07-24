package earth.health.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import earth.health.R
import earth.health.ui.theme.HealthTheme
import earth.health.router.Destination

@Composable
fun HomeScreen(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(title = stringResource(id = R.string.food), text = "1920/1950 kcal", mainAction = {})
        Card(title = stringResource(id = R.string.weight), text = "80 kg", mainAction = {})
    }
}

@Preview()
@Composable
fun HomePreview() {
    HealthTheme {
        HomeScreen(rememberNavController())
    }
}