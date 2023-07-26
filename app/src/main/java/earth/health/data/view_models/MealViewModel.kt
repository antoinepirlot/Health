package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Day
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals
import earth.health.data.entity.getDefaultMeals
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.NoSuchElementException


class MealViewModel(application: Application): AndroidViewModel(application) {
    val meals = mutableStateListOf<Meal>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()

    init {
        viewModelScope.launch {
            meals.addAll(mealDAO.getAll())
        }
    }

    fun readMeal(id: Long) = meals.first { it.id == id }

    fun createMeal(meal: Meal) {
        viewModelScope.launch {
            mealDAO.insert(meal = meal)
            meals.add(meal)
        }
    }

    fun getLastMeal(): Meal {
        return try {
            meals.first { it.day == 0 }
        } catch (err: NoSuchElementException) {
            val latestMeal = Meal(name = Meals.BREAKFAST, day = 0)
            createMeal(latestMeal)
            latestMeal
        }
    }
}