package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods
import kotlinx.coroutines.launch


class MealViewModel(application: Application) : AndroidViewModel(application) {
    val mealWithFoodsList = mutableStateListOf<MealWithFoods>()
    val selectedMeal = mutableStateOf<Meal?>(null)
    val selectedMealFoodList = mutableStateListOf<Food>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()

    init {
        viewModelScope.launch {
            mealWithFoodsList.addAll(mealDAO.getAll())
        }
    }

    fun readMealWithFoods(mealId: Long) = mealWithFoodsList.first { it.meal.id == mealId }

    fun reloadAll() {
        viewModelScope.launch {
            mealWithFoodsList.clear()
            mealWithFoodsList.addAll(mealDAO.getAll())
        }
    }


    fun create(meal: Meal) {
        viewModelScope.launch {
            mealDAO.upsert(meal = meal)
            mealWithFoodsList.add(MealWithFoods(meal, mutableStateListOf()))
        }
    }

    fun isEmpty() = mealWithFoodsList.isEmpty()

    /**
     * Checks if the food is already used in a meal
     */
    fun foodIsUsed(food: Food): Boolean {
        for (mealWithFoods in mealWithFoodsList) {
            if (mealWithFoods.foods.contains(food)) {
                return true
            }
        }
        return false
    }

    /**
     * Store selected meal for mutable states
     */
    fun selectMeal(mealWithFoods: MealWithFoods) {
        selectedMeal.value = mealWithFoods.meal
        selectedMealFoodList.clear()
        selectedMealFoodList.addAll(mealWithFoods.foods)
    }
}