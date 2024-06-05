/*
 * This file is part of Health.
 *
 *  Health is free software: you can redistribute it and/or modify it under
 *  the terms of the GNU General Public License as published by the Free Software Foundation,
 *  either version 3 of the License, or (at your option) any later version.
 *
 *  Health is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along with Health.
 *  If not, see <https://www.gnu.org/licenses/>.
 *
 *  **** INFORMATIONS ABOUT THE AUTHOR *****
 *  The author of this file is Antoine Pirlot, the owner of this project.
 *  You find this original project on github.
 *
 *  My github link is: https://github.com/antoinepirlot
 *  This current project's link is: https://github.com/antoinepirlot/Health
 *
 *  You can contact me via my email: pirlot.antoine@outlook.com
 *  PS: I don't answer quickly.
 */

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
import earth.health.data.utils.removeElement
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

    fun remove(mealWithFoods: MealWithFoods, food: Food, day: Day) {
        viewModelScope.launch {
            val mealId = mealWithFoods.meal.id
            mealFoodCrossRefDao.remove(mealId = mealId, foodId = food.id)
            updateKcal(mealWithFoods = mealWithFoods, day = day)
            mealWithFoods.foods = removeElement(list = mealWithFoods.foods, element = food)
        }
    }
}