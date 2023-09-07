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
}