package earth.health.ui.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import earth.health.R

@Composable
fun AddFoodScreen(
    modifier: Modifier = Modifier
) {
    Text(text = stringResource(id = R.string.add_food_screen))
    Column(modifier = modifier) {
        Row(modifier = modifier) {

        }
    }
}

@Preview
@Composable
fun AddFoodScreenPreview() {
    AddFoodScreen()
}