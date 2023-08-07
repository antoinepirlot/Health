package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.MealFoodCrossRef
import kotlinx.coroutines.launch

class MealFoodCrossRefViewModel(application: Application): AndroidViewModel(application) {
    private val mealFoodCrossRefDao = HealthDatabase.getDatabase(application).mealFoodCrossRefDao()

    fun getQuantity(meal: Meal, food: Food): MutableState<String> {
        var quantity = mutableStateOf("0.0")
        viewModelScope.launch {
            quantity.value = mealFoodCrossRefDao.getQuantity(mealId = meal.id, foodId = food.id).toString()
        }
        return quantity
    }

    fun insert(meal: Meal, food: Food, quantity: Double) {
        val mealFoodCrossRef = MealFoodCrossRef(mealId = meal.id, foodId = food.id, quantity)
        viewModelScope.launch {
            mealFoodCrossRefDao.insert(mealFoodCrossRef)
        }
    }
}