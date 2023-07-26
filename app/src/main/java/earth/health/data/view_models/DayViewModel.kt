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
        viewModelScope.launch {
            val dbDays = dayDAO.getAll()
            days.addAll(dbDays)
        }
    }

    fun startNewDay(): Day {
        /**
         * Create a new day and add it to the days list
         * @return the Day of today
         */
        if (days.isEmpty() || !days[days.lastIndex].date.isBefore(LocalDate.now())) {
            val newDay = Day()
            viewModelScope.launch {
                dayDAO.insert(newDay)
                days.add(newDay)
            }
        }
        return days[days.lastIndex]
    }
}