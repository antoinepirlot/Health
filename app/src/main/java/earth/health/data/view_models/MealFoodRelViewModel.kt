package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import kotlinx.coroutines.launch

class MealFoodRelViewModel(application: Application): AndroidViewModel(application) {

    private val mealFoodRelDao = HealthDatabase.getDatabase(application).mealFoodRelDao()

    fun getAllFoodOf(meal: Meal): SnapshotStateList<Food> {
        val foodListOfMeal = mutableStateListOf<Food>()
        viewModelScope.launch {
            foodListOfMeal.addAll(mealFoodRelDao.getAllFoodOf(meal.id))
        }
        return foodListOfMeal
    }
}