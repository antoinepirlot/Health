package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Day
import earth.health.data.entity.Food
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals
import earth.health.data.entity.relations.DayWithMeals
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import java.time.LocalDate

class DayViewModel(application: Application): AndroidViewModel(application) {
    val days = mutableStateListOf<Day>()
    val daysWithMeals = mutableStateListOf<DayWithMeals>()

    private val dayDAO = HealthDatabase.getDatabase(application).dayDao()

    init {
        days.clear()
        viewModelScope.launch {
            val dbDaysWithMeals = dayDAO.getAll()
            for (dayWithMeals in dbDaysWithMeals) {
                days.add(dayWithMeals.day)
            }
            daysWithMeals.addAll(dbDaysWithMeals)
        }
    }

    /**
     * Create a new day and add it to the days list
     */
    fun startNewDay() {
        if (days.isEmpty()) {
            create()
            return
        }
        val lastDay = days.last()
        if (lastDay.date.isBefore(LocalDate.now())) {
            create()
        }
    }

    private fun create() {
        viewModelScope.launch {
            val newDay = Day()
            dayDAO.upsert(newDay)
            days.add(newDay)
            newDay.id = nextId()
            val meals = listOf<Meal>(
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

    fun getDayOfMeal(meal: Meal): Day {
        for (dayWithMeals in daysWithMeals) {
            for (tempMeal in dayWithMeals.meals) {
                if (tempMeal.id == meal.id) {
                    return dayWithMeals.day
                }
            }
        }
        throw IllegalStateException("Technically, the meal must have a day")
    }

    private fun nextId(): Long {
        if (days.lastIndex == -1)
            return 1
        return (days.lastIndex + 1).toLong()
    }

    fun updateKcal(meal: Meal, food: Food, quantity: String) {
        viewModelScope.launch {
            val day = getDayOfMeal(meal)
            day.totalKcal += (food.kcal.toDouble() * quantity.toDouble()).toInt()
            dayDAO.upsert(day)
        }
    }

    fun reloadDay(dayId: Long) {
        viewModelScope.launch {
            val dbDay = dayDAO.getDay(dayId)
            daysWithMeals.first { it.day.id == dayId }.day.totalKcal = dbDay.day.totalKcal
            days.first { it.id == dayId }.totalKcal = dbDay.day.totalKcal
        }
    }
}