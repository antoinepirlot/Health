package earth.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodMealRelViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.router.Router
import earth.health.ui.theme.HealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {
                Router()
            }
        }
    }
}

@Preview
@Composable
fun ComponentActivityPreview() {
    ComponentActivity()
}