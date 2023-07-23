package earth.health.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import earth.health.ui.theme.HealthTheme

@Composable
fun HomeScreen() {
    Card(title = "Nourriture", text = "1920/1950 kcal", button = {})
}

@Preview()
@Composable
fun HomePreview() {
    HealthTheme {
        HomeScreen()
    }
}