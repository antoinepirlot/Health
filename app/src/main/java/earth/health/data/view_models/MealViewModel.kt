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
            mealWithFoodsList.addAll(mealDAO.getAllWithFoods())
        }
    }

    fun readMealWithFoods(mealId: Long) = mealWithFoodsList.first { it.meal.id == mealId }

    fun reloadAll() {
        viewModelScope.launch {
            mealWithFoodsList.clear()
            mealWithFoodsList.addAll(mealDAO.getAllWithFoods())
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