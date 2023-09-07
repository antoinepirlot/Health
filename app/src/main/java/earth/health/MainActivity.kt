package earth.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealWithFoodsViewModel
import earth.health.router.Router
import earth.health.ui.theme.HealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {
                val dayViewModel = viewModel<DayViewModel>()
                val mealWithFoodsViewModel = viewModel<MealWithFoodsViewModel>()
                val mealFoodCrossRefViewModel = viewModel<MealFoodCrossRefViewModel>()
                val foodViewModel = viewModel<FoodViewModel>()

                Surface(Modifier.fillMaxSize()) {
                    Router(
                        dayViewModel = dayViewModel,
                        mealWithFoodsViewModel = mealWithFoodsViewModel,
                        mealFoodCrossRefViewModel = mealFoodCrossRefViewModel,
                        foodViewModel = foodViewModel,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ComponentActivityPreview() {
    ComponentActivity()
}