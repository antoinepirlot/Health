package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.relations.FoodWithMeals
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    val foodWithMealsList = mutableStateListOf<FoodWithMeals>()
    val foodList = mutableStateListOf<Food>()

    private val foodDAO = HealthDatabase.getDatabase(application).foodDAO()

    init {
        viewModelScope.launch {
            val dbFoodWithMeals = foodDAO.getAll()
            for (foodWithMeals in dbFoodWithMeals) {
                foodList.add(foodWithMeals.food)
            }
            foodWithMealsList.addAll(dbFoodWithMeals)
        }
    }

    fun readFood(id: Long) = foodWithMealsList.first { it.food.id == id }

    fun createFood(food: Food) {
        viewModelScope.launch {
            foodDAO.insert(food = food)
            foodWithMealsList.add(FoodWithMeals(food, listOf()))
            foodList.add(food)
        }
    }
}