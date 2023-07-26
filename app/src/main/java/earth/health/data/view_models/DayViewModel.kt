package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Day
import kotlinx.coroutines.launch
import java.time.LocalDate

class DayViewModel(application: Application): AndroidViewModel(application) {
    val days = mutableStateListOf<Day>()

    private val dayDAO = HealthDatabase.getDatabase(application).dayDao()

    init {
        // TODO logcat says it's still empty, apparently, the code is not launched here
        viewModelScope.launch {
            val dbDays = dayDAO.getAll()
            if (dbDays.isEmpty())
                startNewDay()
            else
                days.addAll(dbDays)
        }
    }

    fun startNewDay(): Day {
        /**
         * Create a new day and add it to the days list
         * @return the Day of today
         */
        val newDay = Day()
        viewModelScope.launch {
            dayDAO.insert(newDay)
            days.add(newDay)
        }
        return newDay
    }
}