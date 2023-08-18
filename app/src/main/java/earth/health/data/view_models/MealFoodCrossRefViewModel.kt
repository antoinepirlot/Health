package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Day
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.MealFoodCrossRef
import earth.health.data.entity.relations.MealWithFoods
import earth.health.data.utils.getTotalKcal
import kotlinx.coroutines.launch

class MealFoodCrossRefViewModel(application: Application): AndroidViewModel(application) {
    private val mealFoodCrossRefDao = HealthDatabase.getDatabase(application).mealFoodCrossRefDao()

    fun getQuantity(meal: Meal, food: Food): MutableState<String> {
        val quantity = mutableStateOf("0.0")
        viewModelScope.launch {
            try {
                quantity.value = mealFoodCrossRefDao.getQuantity(mealId = meal.id, foodId = food.id).toString()
            } catch (_: Throwable) { //TODO find the right exception to avoid catching anything

            }
        }
        return quantity
    }

    fun insert(mealWithFoods: MealWithFoods, day: Day, food: Food, quantity: Double) {
        viewModelScope.launch {
            val mealFoodCrossRef = MealFoodCrossRef(mealId = mealWithFoods.meal.id, foodId = food.id, quantity)
            mealFoodCrossRefDao.upsert(mealFoodCrossRef)
            updateKcal(mealWithFoods = mealWithFoods, day = day)
        }
    }

    private fun updateKcal(mealWithFoods: MealWithFoods, day: Day) {
        viewModelScope.launch {
            val meal = mealWithFoods.meal
            val oldKcal = meal.totalKcal
            meal.totalKcal = getTotalKcal(
                mealWithFoods = mealWithFoods,
                mealFoodCrossRef = mealFoodCrossRefDao.getAll()
            )
            mealFoodCrossRefDao.updateMealTotalKcal(mealId = meal.id, totalKcal = meal.totalKcal)
            val dayTotalKcalToUpdate = meal.totalKcal - oldKcal
            mealFoodCrossRefDao.updateDayTotalKcal(dayId = meal.dayId, totalKcalToUpdate = dayTotalKcalToUpdate)
            day.totalKcal += dayTotalKcalToUpdate
        }
    }
}