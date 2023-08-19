package earth.health.data.view_models

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import earth.health.data.HealthDatabase
import earth.health.data.entity.Food
import earth.health.data.entity.getBlankFood
import earth.health.data.entity.relations.FoodWithMeals
import earth.health.data.entity.relations.getBlankFoodWithMeals
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    val foodWithMealsList = mutableStateListOf<FoodWithMeals>()
    val foodList = mutableStateListOf<Food>()

    private val foodDAO = HealthDatabase.getDatabase(application).foodDAO()

    init {
        foodWithMealsList.clear()
        foodList.clear()
        viewModelScope.launch {
            val dbFoodWithMeals = foodDAO.getAll()
            for (foodWithMeals in dbFoodWithMeals) {
                foodList.add(foodWithMeals.food)
            }
            foodWithMealsList.addAll(dbFoodWithMeals)
        }
    }

    fun readFoodWithMeals(foodId: Long): MutableState<FoodWithMeals> {
        val foodWithMeals = mutableStateOf(getBlankFoodWithMeals())
        foodWithMeals.value = foodWithMealsList.first { it.food.id == foodId }
        return foodWithMeals
    }

    fun readFood(foodId: Long): MutableState<Food> {
        val food = mutableStateOf(getBlankFood())
        food.value = foodList.first { it.id == foodId }
        return food
    }

    fun createFood(food: Food) {
        viewModelScope.launch {
            food.id = foodDAO.insert(food = food)
            foodWithMealsList.add(FoodWithMeals(food, mutableStateListOf()))
            foodList.add(food)
        }
    }
}