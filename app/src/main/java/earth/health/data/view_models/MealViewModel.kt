package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {
    val meals = mutableStateListOf<Meal>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()

    init {
        viewModelScope.launch {
            meals.addAll(mealDAO.getAll())
        }
    }

    fun readMeal(id: Long) = meals.first { it.id == id }

    fun getMealWithFoods(mealId: Long): MealWithFoods {
        var mealWithFoods: MealWithFoods? = null
        viewModelScope.launch {
            mealWithFoods = mealDAO.getAllMealWithFoods().first() { it.meal.id == mealId }
        }
        return mealWithFoods!!
    }

    fun create(meal: Meal) {
        viewModelScope.launch {
            mealDAO.insert(meal = meal)
            meals.add(meal)
        }
    }
}