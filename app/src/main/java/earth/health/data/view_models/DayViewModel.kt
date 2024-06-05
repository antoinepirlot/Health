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
import earth.health.data.entity.Day
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * @author Antoine Pirlot on 26/07/2023
 */

class DayViewModel(application: Application): AndroidViewModel(application) {

    val days = mutableStateListOf<Day>()
    val isLoaded = mutableStateOf(false)
    val selectedDay = mutableStateOf<Day?>(null)

    private val dayDAO = HealthDatabase.getDatabase(application).dayDao()

    init {
        viewModelScope.launch {
            val dbDays = dayDAO.getAll()
            days.addAll(dbDays)
            startNewDay()
            selectedDay.value = getLastDay()
            isLoaded.value = true
        }
     }

    fun readOneByMeal(meal: Meal): Day = days.first { it.id == meal.dayId }


    /**
     * Create a new day and add it to the days list
     */
    fun startNewDay() {
        viewModelScope.launch {
            if (days.size > (0).toLong() && getLastDay().date.isEqual(LocalDate.now())) {
                return@launch
            }
            val newDay = Day()
            newDay.id = nextId()
            days.add(newDay)
            dayDAO.upsert(newDay)
            val meals = listOf(
                Meal(name = Meals.BREAKFAST, dayId = newDay.id),
                Meal(name = Meals.LUNCH, dayId = newDay.id),
                Meal(name = Meals.DINNER, dayId = newDay.id),
                Meal(name = Meals.EXTRAS, dayId = newDay.id),
            )
            for (meal in meals) {
                dayDAO.insertMeal(meal)
            }
        }
    }

    fun getLastDay(): Day = days.last()

    private fun nextId() : Long = (days.size + 1).toLong()

    suspend fun exportData(): List<Day> {
        return dayDAO.getAll()
    }
}