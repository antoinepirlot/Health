package earth.health.data.view_models

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.R
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.relations.MealWithFoods
import earth.health.data.utils.addElement
import earth.health.data.utils.removeElement
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class MealWithFoodsViewModel(application: Application) : AndroidViewModel(application) {
    val mealWithFoodsList = mutableStateListOf<MealWithFoods>()

    private val mealDAO = HealthDatabase.getDatabase(application).mealDAO()
    private val mealFoodCrossRefDAO = HealthDatabase.getDatabase(application).mealFoodCrossRefDao()

    init {
        viewModelScope.launch {
            mealWithFoodsList.addAll(mealDAO.getAll())
        }
    }

    fun readMealWithFoods(mealId: Long) = mealWithFoodsList.first { it.meal.id == mealId }

    fun reloadAll() {
        viewModelScope.launch {
            val dbMealList = mealDAO.getAll()
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
}