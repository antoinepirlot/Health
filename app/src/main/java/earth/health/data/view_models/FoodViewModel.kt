package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.getBlankFood
import earth.health.data.entity.relations.FoodWithMeals
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    val foodList = mutableStateListOf<Food>()

    private val foodDAO = HealthDatabase.getDatabase(application).foodDAO()

    init {
        viewModelScope.launch {
            foodList.addAll(foodDAO.getAll())
        }
    }

    fun readFood(foodId: Long) = foodList.first { it.id == foodId }

    fun upsert(food: Food) {
        viewModelScope.launch {
            food.id = foodDAO.upsert(food = food)
        }
    }
}