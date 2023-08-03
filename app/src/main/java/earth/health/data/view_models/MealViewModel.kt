package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {
    val mealWithFoods = mutableStateListOf<MealWithFoods>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()

    init {
        viewModelScope.launch {
            mealWithFoods.addAll(mealDAO.getAll())
        }
    }

    fun readMeal(id: Long) = mealWithFoods.first { it.meal.id == id }


    fun create(meal: Meal) {
        viewModelScope.launch {
            mealDAO.insert(meal = meal)
            mealWithFoods.add(MealWithFoods(meal, listOf()))
        }
    }
}