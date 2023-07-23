package earth.health.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R
import earth.health.ui.theme.HealthTheme

@Composable
fun HomeScreen() {
    Column() {
        Card(title = stringResource(id = R.string.food), text = "1920/1950 kcal", button = {})
        Card(title = stringResource(id = R.string.weight), text = "80 kg", button = {})
    }
}

@Preview()
@Composable
fun HomePreview() {
    HealthTheme {
        HomeScreen()
    }
}