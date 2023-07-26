package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.FoodMealRel
import kotlinx.coroutines.launch

class FoodMealRelViewModel(application: Application): AndroidViewModel(application) {
    val foodMealRelList = mutableStateListOf<FoodMealRel>()

    private val foodMealRelDAO = HealthDatabase.getDatabase(application).foodMealRelDao()

    init {
        viewModelScope.launch {
            val dbFoodMealRel = foodMealRelDAO.getAll()
            foodMealRelList.addAll(dbFoodMealRel)
        }
    }

    fun insert(foodMealRel: FoodMealRel) {
        viewModelScope.launch {
            foodMealRelDAO.insert(foodMealRel)
            foodMealRelList.add(foodMealRel)
        }
    }
}