package earth.health.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import earth.health.ui.theme.HealthTheme

@Composable
fun Card(modifier: Modifier = Modifier, title: String, text: String, button: () -> Unit) {
    Button(onClick = { /*TODO*/ }) {
        Column {
            Text(text = title, style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold))
            Text(text = text, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Light))
        }
        ElevatedButton(onClick = { /*TODO*/ }) {
            Text(text = "Touch here")
        }
    }
}

@Preview
@Composable
fun CardPreview() {
    HealthTheme {
        Card(title = "Nourriture", text = "1920/1950 kcal", button = {})
    }
}