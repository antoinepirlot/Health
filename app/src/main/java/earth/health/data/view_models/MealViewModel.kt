package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Meal
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {
    val meals = mutableStateListOf<Meal>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()

    init {
        viewModelScope.launch {
            val dbMeals = mealDAO.getAll()
            meals.addAll(dbMeals)
        }
    }

    fun readMeal(id: Long) = meals.first { it.id == id }

    fun createMeal(meal: Meal) {
        val mealToAdd = meal.copy()
        viewModelScope.launch {
            mealDAO.insert(meal = mealToAdd)
            meals.add(mealToAdd)
        }
    }
}