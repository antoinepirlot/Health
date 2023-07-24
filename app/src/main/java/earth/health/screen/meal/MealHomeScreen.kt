package earth.health.screen.meal

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R
import earth.health.screen.Card

@Composable
fun MealHomeScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(title = stringResource(id = R.string.breakfast), text = "null", button = {})
        Card(title = stringResource(id = R.string.lunch), text = "null", button = {})
        Card(title = stringResource(id = R.string.dinner), text = "null", button = {})
        Card(title = stringResource(id = R.string.extras), text = "null", button = {})
    }
}

@Preview
@Composable
fun MealHomeScreenPreview() {
    MealHomeScreen()
}