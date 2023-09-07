package earth.health

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import earth.health.data.view_models.DayViewModel
import earth.health.data.view_models.FoodViewModel
import earth.health.data.view_models.MealFoodCrossRefViewModel
import earth.health.data.view_models.MealViewModel
import earth.health.router.Router
import earth.health.ui.theme.HealthTheme
import earth.health.ui.utils.HealthTopAppBar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthTheme {
                val modifier = Modifier

                val dayViewModel = viewModel<DayViewModel>()
                val mealViewModel = viewModel<MealViewModel>()
                val mealFoodCrossRefViewModel = viewModel<MealFoodCrossRefViewModel>()
                val foodViewModel = viewModel<FoodViewModel>()

                Surface(modifier = modifier.fillMaxSize()) {
                    Scaffold (
                        modifier = modifier,
                        topBar = {
                            HealthTopAppBar(modifier = modifier)
                        },
                    ) { innerPadding ->
                        Router(
                            modifier = modifier.padding(innerPadding),
                            dayViewModel = dayViewModel,
                            mealViewModel = mealViewModel,
                            mealFoodCrossRefViewModel = mealFoodCrossRefViewModel,
                            foodViewModel = foodViewModel,
                        )
                    }
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