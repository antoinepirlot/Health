package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.getDefaultFood
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    val foodList = mutableStateListOf<Food>()

    private val foodDAO = HealthDatabase.getDatabase(application).foodDAO()

    init {
        viewModelScope.launch {
            foodList.addAll(foodDAO.getAll())
        }
    }

    fun readFood(id: Long) = foodList.first { it.id == id }

    fun createFood(food: Food) {
        val foodToAdd = food.copy()
        viewModelScope.launch {
            foodDAO.insert(food = foodToAdd)
            foodList.add(foodToAdd)
        }
    }
}