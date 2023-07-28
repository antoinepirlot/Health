package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Day
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals
import kotlinx.coroutines.launch
import java.time.LocalDate

class DayViewModel(application: Application): AndroidViewModel(application) {
    val days = mutableStateListOf<Day>()

    private val dayDAO = HealthDatabase.getDatabase(application).dayDao()

    init {
        viewModelScope.launch {
            val dbDays = dayDAO.getAll()
            if (dbDays.isEmpty()) {
                startNewDay()
                return@launch
            }
            if (dbDays.last().date.isBefore(LocalDate.now())) {
                startNewDay()
            }
            days.addAll(dbDays)
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
            dayDAO.insert(newDay)
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

    fun nextId(): Long {
        if(days.lastIndex == -1)
            return 1
        return (days.lastIndex + 1).toLong()
    }
}