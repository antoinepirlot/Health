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
import earth.health.data.entity.relations.getBlankFoodWithMeals
import kotlinx.coroutines.launch

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private val foodDAO = HealthDatabase.getDatabase(application).foodDAO()

    fun getAll(): SnapshotStateList<Food> {
        val foodList = mutableStateListOf<Food>()
        viewModelScope.launch {
            foodList.addAll(foodDAO.getAll())
        }
        return foodList
    }

    fun getAllWithMeals(): SnapshotStateList<FoodWithMeals> {
        val foodList = mutableStateListOf<FoodWithMeals>()
        viewModelScope.launch {
            foodList.addAll(foodDAO.getAllWithMeals()
            )
        }
        return foodList
    }

    fun readFoodWithMeals(foodId: Long): MutableState<FoodWithMeals> {
        val foodWithMeals = mutableStateOf(getBlankFoodWithMeals())
        viewModelScope.launch {
            foodWithMeals.value = foodDAO.getOneWithMeals(foodId = foodId)
        }
        return foodWithMeals
    }

    fun readFood(foodId: Long): MutableState<Food> {
        val food = mutableStateOf(getBlankFood())
        viewModelScope.launch {
            food.value = foodDAO.getOne(foodId = foodId)
        }
        return food
    }

    fun upsert(food: Food) {
        viewModelScope.launch {
            food.id = foodDAO.upsert(food = food)
        }
    }
}