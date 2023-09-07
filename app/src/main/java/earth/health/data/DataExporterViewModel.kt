package earth.health.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.entity.serialize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class DataExporterViewModel(application: Application): AndroidViewModel(application) {
    private val db: HealthDatabase = HealthDatabase.getDatabase(application)
    private val dayDAO = db.dayDao()
    private val foodDAO = db.foodDAO()
    private val mealDAO = db.mealDAO()
    private val mealFoodCrossRefDao = db.mealFoodCrossRefDao()

    fun exportData() {
        viewModelScope.launch {
            val days = dayDAO.getAll()
            val meals = mealDAO.getAll()
            val foods = foodDAO.getAll()
            val mealFoodCrossRef = mealFoodCrossRefDao.getAll()

            // TODO fix serializing date
            var json = serialize(days)
            json += serialize(meals)
            json += serialize(foods)
            json += serialize(mealFoodCrossRef)

        }
    }
}