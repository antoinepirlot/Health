package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Day
import earth.health.data.entity.Meal
import earth.health.data.entity.Meals
import earth.health.data.entity.getBlankDay
import kotlinx.coroutines.launch
import java.time.LocalDate

class DayViewModel(application: Application): AndroidViewModel(application) {

    val days = mutableStateListOf<Day>()
    val isLoaded = mutableStateOf(false)

    private val dayDAO = HealthDatabase.getDatabase(application).dayDao()

    init {
        viewModelScope.launch {
            val dbDays = dayDAO.getAll()
            days.addAll(dbDays)
            if (dbDays.isNotEmpty() && dayDAO.getLastDay().date.isEqual(LocalDate.now())) {
                isLoaded.value = true
                return@launch
            }
            val newDay = Day()
            newDay.id = dayDAO.nextId()
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
            isLoaded.value = true
        }
     }

    fun getOne(meal: Meal): MutableState<Day> {
        val day = mutableStateOf(getBlankDay())
        viewModelScope.launch {
            day.value = dayDAO.getOneWithMeals(meal.dayId).day
        }
        return day
    }


    /**
     * Create a new day and add it to the days list
     */
    fun startNewDay(): MutableState<Day> {
        val dayToReturn = mutableStateOf(getBlankDay())
        viewModelScope.launch {
            if (dayDAO.count() > (0).toLong() && dayDAO.getLastDay().date.isEqual(LocalDate.now())) {
                return@launch
            }
            val newDay = Day()
            newDay.id = dayDAO.nextId()
            dayToReturn.value = newDay
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
        return dayToReturn
    }

    fun getLastDay(): Day = days.last()
}